package com.frame.cache.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import com.frame.cache.redis.adapter.JedisAdapter;
import com.frame.cache.redis.adapter.impl.JedisAdapterClusterImpl;
import com.frame.cache.redis.adapter.impl.JedisAdapterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSlotBasedConnectionHandler;
import redis.clients.jedis.exceptions.JedisConnectionException;


/**
 * Jedis工具类 jedis最低版本2.7.2
 *
 * @author 杨雪令
 */
public class JedisUtil {

   /** logger */
   private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    /**
     * redis普通连接
     */
    public static JedisPool jedisPool;
    /**
     * redis普通连接数据库索引
     */
    public static int dbIndex = 1;
    /**
     * 缓存过期时间，单位秒，大于0有效
     */
    public static int expireTime = 180;
    /**
     * CUD不刷新缓存（redis.cud.not.flush.key配置关键字，英文逗号隔开，如果要清理的key包含这些关键字， 则不清理）
     */
    public static List<String> cudNotFlushList = new ArrayList<String>();
    /**
     * key 前缀，开发，测试，部署阶段使用不同前缀，避免缓存数据冲突
     */
    public static String keyPrefix = "Test:";
    /**
     * 编码
     */
    public static final String charsert = "utf-8";
    /**
     * jedis 适配器
     */
    public static JedisAdapter jedisAdapter = null;
    /**
     * 集群模式redis地址前缀
     */
    private static final String redisClusterAddrPrefix = "redis.cluster.addr";
    /**
     * 进行CUD操作时，不刷新缓存，配置前缀
     */
    private static final String cudNotFlushKeyPrefix = "redis.cud.not.flush.key";
    /**
     * 集群模式redis地址正则表达式
     */
    private static Pattern redisClusterHostPatt = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
    /**
     * 集群模式redis节点
     */
    public static Set<HostAndPort> redisClusterNodes = new HashSet<HostAndPort>();
    /**
     * 集群模式redis連接，此連接不關閉，需要輪詢每個redis服務器時使用，避免建立連接花費過長的時間
     */
    public static List<Jedis> clusterConnections = new ArrayList<Jedis>();
    /**
     * redis集群连接
     */
    public static JedisClusterConnectionHandler connectionHandler;
    /**
     * 集群模式如果一個連接失效，會自動嘗試使用其他連接， maxRedirections配置一個請求最多允許嘗試的次數
     */
    public static int maxRedirections = 10;
    /**
     * 最后一次连接redis服务器失败的时间
     */
    private static long lastTimeConnectionError = 0;
    /**
     * 当连接redis服务器失败时，最少间隔多久才能重新尝试，单位秒
     */
    private static final int intervalConnectionError = 600;

    /**
     * redis 配置文件
     */
    private static Properties properties;

    // 静态方式加载redis 配置文件
    static {
        String propName = "redis.properties";
        properties = new Properties();
        InputStream is = null;
        BufferedReader bf = null;
        is = JedisUtil.class.getResourceAsStream("/" + propName);// 将地址加在到文件输入流中
        if (is == null) is = JedisUtil.class.getResourceAsStream(propName);// 将地址加在到文件输入流中
        try {
            bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));// 转为字符流，设置编码为UTF-8防止出现乱码
            properties.load(bf);// properties对象加载文件输入流
        } catch (Exception e) {
            logger.error(propName + "属性文件读取失败");
        } finally {
            try {// 文件流关闭
                if (bf != null) bf.close();
                if (is != null) is.close();
            } catch (IOException e) {
            }
        }
        // 读取缓存配置参数
        keyPrefix = properties.getProperty("redis.key.prefix");
        if (keyPrefix.toLowerCase().equals("auto")) keyPrefix = "TEST-REDIS-" + new Random().nextInt();
        maxRedirections = Integer.valueOf(properties.getProperty("redis.maxRedirections"));
        expireTime = Integer.valueOf(properties.getProperty("redis.expire.time"));

        // 读取cudNotFlushKey配置
        for (Object key : properties.keySet()) {
            if (!((String) key).startsWith(cudNotFlushKeyPrefix)) continue;
            cudNotFlushList.add(properties.get(key).toString());
        }

        //集群模式
        if (properties.getProperty("redis.cluster").equals("1")) {
            // 读取redis节点配置
            for (Object key : properties.keySet()) {
                if (!((String) key).startsWith(redisClusterAddrPrefix)) continue;
                String val = (String) properties.get(key);
                boolean isHost = redisClusterHostPatt.matcher(val).matches();
                if (!isHost) throw new IllegalArgumentException("redis 集群配置 ip 或 port 不合法");
                String[] hostStr = val.split(":");
                HostAndPort host = new HostAndPort(hostStr[0], Integer.valueOf(hostStr[1]));
                redisClusterNodes.add(host);
            }
            //初始化连接池
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
            genericObjectPoolConfig.setMaxWaitMillis(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
            // 创建连接
            connectionHandler = new JedisSlotBasedConnectionHandler(redisClusterNodes, genericObjectPoolConfig, Integer.valueOf(properties.getProperty("redis.timeout")));
            for (HostAndPort hp : JedisUtil.redisClusterNodes) {
                try {
                    clusterConnections.add(JedisUtil.connectionHandler.getConnectionFromNode(hp));
                } catch (Exception e) {
                    logger.error("redis 服务器连接超时：" + hp);
                }
            }
            jedisAdapter = new JedisAdapterClusterImpl();
        } else {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
            config.setMaxWaitMillis(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
            if (properties.getProperty("redis.password").trim().length() > 0)
                jedisPool = new JedisPool(config, properties.getProperty("redis.host"), Integer.valueOf(properties.getProperty("redis.port")), Integer.valueOf(properties.getProperty("redis.timeout")), properties.getProperty("redis.password"));
            else
                jedisPool = new JedisPool(config, properties.getProperty("redis.host"), Integer.valueOf(properties.getProperty("redis.port")), Integer.valueOf(properties.getProperty("redis.timeout")));
            jedisAdapter = new JedisAdapterImpl();
        }
    }

    /**
     * 是否允许和redis服务器建立连接
     *
     * @date 2015年11月8日 下午12:17:53
     * @author yxl
     */
    private static boolean allowConnection() {
        //当前时间 - 最后一次连接redis服务器失败的时间 < 当连接redis服务器失败时，最少间隔多久才能重新尝试，则不允许和redis服务器建立连接
        if (System.currentTimeMillis() - lastTimeConnectionError < intervalConnectionError * 1000) {
            logger.error("警告：当前操作未使用redis缓存，因为获取redis连接失败，请检查服务器配置，系统将在" + (System.currentTimeMillis() - lastTimeConnectionError - intervalConnectionError * 1000) / 1000 + "秒后重新尝试连接...");
            return false;
        }
        return true;
    }

    /**
     * 设置缓存
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static void set(final String key, final Object value) throws Exception {
        if (!allowConnection()) return;
        try {
            jedisAdapter.set(JedisUtil.keyPrefix + key, value);
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
        }
    }

    /**
     * get缓存
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static Object get(final String key) throws Exception {
        if (!allowConnection()) return null;
        try {
            return jedisAdapter.get(JedisUtil.keyPrefix + key);
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
            return null;
        }
    }

    /**
     * delete缓存
     * 可以使用*匹配
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static void delete(String key) throws Exception {
        if (!allowConnection()) return;
        // 根据规则匹配关键字不主动删除缓存
        for (String cudNotFlushKey : cudNotFlushList) {
            if (key.contains(cudNotFlushKey)) {
                logger.info(cudNotFlushKey + " don't need clear on CUD...");
                return;
            }
        }

        try {
            //自动匹配key
            if (key.contains("*")) {
                for (byte[] childKey : getKeys(key)) delete(new String(childKey));
            } else {
                if (!key.startsWith(JedisUtil.keyPrefix)) key = JedisUtil.keyPrefix + key;
                logger.info("delete redis by key：" + key);
                jedisAdapter.delete(key);
            }
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
        }
    }

    /**
     * 根据条件查询key
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static Set<byte[]> getKeys(final String key) throws Exception {
        if (!allowConnection()) return new HashSet<byte[]>();
        try {
            return jedisAdapter.getKeys(JedisUtil.keyPrefix + key);
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
            return new HashSet<byte[]>();
        }
    }


    /**
     * 清除redis所有缓存
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static void flushAll() throws Exception {
        if (!allowConnection()) return;
        try {
            jedisAdapter.flushAll();
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
        }
    }


    /**
     * 清除项目所有缓存
     *
     * @date 2015年10月23日 下午6:15:33
     * @author yxl
     */
    public static void clearAllOfProject() throws Exception {
        if (!allowConnection()) return;
        try {
            for (byte[] keyByte : getKeys("*")) {
                jedisAdapter.delete(new String(keyByte));
            }
        } catch (JedisConnectionException e) {
            lastTimeConnectionError = System.currentTimeMillis();
            logger.error("警告：获取redis连接失败，请检查服务器配置...");
        }
    }

    /**
     * test
     *
     * @date 2015年10月26日 上午10:50:29
     * @author yxl
     */
    public static void main(String[] args) throws Exception {

        Map map = new HashedMap();
        map.put("tss", "ss");
        JedisUtil.set("JedisUtil:Test:Map", map);
        for (int i = 1; i <= 500; i++) {
            JedisUtil.set("com.test." + i, "t" + i + ".data...");
        }

        //Map newMap = (Map) JedisUtil.get("JedisUtil:Test:Map");
        //System.out.println(newMap.get("t2"));

        Set<byte[]> keys = getKeys("com.t*");
        for (byte[] key : keys) {
            System.out.println(new String(key));
        }
        System.out.println("total: " + keys.size());

        //delete("com.t*");

        keys = getKeys("com.t*");
        for (byte[] key : keys) {
            System.out.println(new String(key));
        }
        System.out.println("total: " + keys.size());
    }
}