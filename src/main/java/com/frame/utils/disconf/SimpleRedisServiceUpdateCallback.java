package com.frame.utils.disconf;

import com.frame.cache.redis.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

/**
 * 更新Redis配置时的回调函数
 *
 * @author liaoqiqi
 * @version 2014-6-17
 */
@Service
@Scope("singleton")
@DisconfUpdateService(classes = {JedisConfig.class})
public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SimpleRedisServiceUpdateCallback.class);

    @Autowired
    private SimpleRedisService simpleRedisService;

    /**
     *
     */
    public void reload() throws Exception {
        simpleRedisService.changeJedis();
    }

}
