package com.frame.utils.encrypt;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * RSA加密与解密
 * author yefeng
 */
public class RSAUtil {

    private RSAUtil() {
    }

    /**
     * 签名算法
     */
    public enum SignatureType {
        SHA1withRSA("SHA1withRSA"),
        MD5WithRSA("MD5WithRSA"),
        REP("RSA/ECB/PKCS1Padding");
        private String value;

        SignatureType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

    }

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
     */
    public static final int KEY_SIZE = 1024;

    /**
     * 默认签名类型
     */
    public static final SignatureType SIGNATURE_TYPE = SignatureType.SHA1withRSA;

    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return map:包含公钥|私钥,以使用Base64编码
     */
    public static RSAEntity generateKey() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return new RSAEntity(keyPair.getPrivate(), keyPair.getPublic());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return 签名字符串
     */
    public static String sign(byte[] data, PrivateKey privateKey) {
        return sign(data, privateKey, SIGNATURE_TYPE);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data          已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @param signatureType 签名类型
     * @return 签名字符串
     */
    public static String sign(byte[] data, PrivateKey privateKey, SignatureType signatureType) {
        try {
            //实例化
            Signature signature = Signature.getInstance(signatureType.getValue());
            //初始化，传入私钥
            signature.initSign(privateKey);
            //更新
            signature.update(data);
            //签名
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验数字签名
     *
     * @param data      已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @return true验签通过 false验签失败
     */
    public static boolean verify(byte[] data, PublicKey publicKey, String sign) {
        return verify(data, publicKey, sign, SIGNATURE_TYPE);
    }

    /**
     * 校验数字签名
     *
     * @param data          已加密数据
     * @param publicKey     公钥(BASE64编码)
     * @param sign          数字签名
     * @param signatureType 签名类型
     * @return true验签通过 false验签失败
     */
    public static boolean verify(byte[] data, PublicKey publicKey, String sign, SignatureType signatureType) {
        try {
            //实例化
            Signature signature = Signature.getInstance(signatureType.getValue());
            //初始化
            signature.initVerify(publicKey);
            //更新
            signature.update(data);
            //验签
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 加密(公钥加密私钥解密,私钥加密公钥解密)
     *
     * @param data 需要加密的数据
     * @param key  公钥或者私钥
     * @return 加密后的字节数组
     */
    public static byte[] encrypt(byte[] data, Key key) {
        return splitData(data, key, 0);
    }

    /**
     * 公钥解密
     *
     * @param data 已加密数据
     * @param key  公钥(BASE64编码)
     * @return 解密结果
     */
    public static byte[] decrypt(byte[] data, Key key) {
        return splitData(data, key, 1);
    }

    /**
     * 对数据分段加密或解密
     *
     * @param data 加密或解密源
     * @param key  公钥或者私钥
     * @param type 0:加密,1解密
     * @return 加密或解密结果
     */
    public static byte[] splitData(byte[] data, Key key, int type) {
        ByteArrayOutputStream out = null;
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(type == 0 ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key);
            out = new ByteArrayOutputStream();
            int inputLen = data.length;
            int offSet = 0;
            byte[] cache;
            int i = 0;
            int max = (type == 0 ? MAX_ENCRYPT_BLOCK : MAX_DECRYPT_BLOCK);
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > max) {
                    cache = cipher.doFinal(data, offSet, max);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * max;
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 恢复公钥
     *
     * @param keyBytes 公钥字节
     * @return 公钥对象
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 恢复私钥
     *
     * @param keyBytes 私钥字节
     * @return 私钥对象
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 恢复公钥
     *
     * @param publicKey 已被base64转码的公钥字符串
     * @return 还原成公钥对象
     */
    public static PublicKey restorePublicKey(String publicKey) {
        return restorePublicKey(Base64.getDecoder().decode(publicKey));
    }

    /**
     * 恢复私钥
     *
     * @param privateKey 已被base64转码的私钥字符串
     * @return 还原成私钥对象
     */
    public static PrivateKey restorePrivateKey(String privateKey) {
        return restorePrivateKey(Base64.getDecoder().decode(privateKey));
    }

    
    public static void main(String[] args) {
        RSAEntity entity = RSAUtil.generateKey();
        assert entity != null;
        System.out.println("私钥:" + entity.getPrivateKeyStr());
        System.out.println("公钥:" + entity.getPublicKeyStr());
        String source = "1234567890";
        System.out.println("加密数据:" + source);
        byte[] prb = RSAUtil.encrypt(source.getBytes(), entity.getPrivateKey());
        System.out.println("加密结果:" + new String(Base64.getEncoder().encode(prb)));
        byte[] pub = RSAUtil.decrypt(prb, entity.getPublicKey());
        System.out.println("解密结果:" + new String(pub));
        String sign = RSAUtil.sign(prb, entity.getPrivateKey());
        System.out.println("签名结果:" + sign);
        boolean flag = RSAUtil.verify(prb, entity.getPublicKey(), sign);
        System.out.println(flag);
    }
}
