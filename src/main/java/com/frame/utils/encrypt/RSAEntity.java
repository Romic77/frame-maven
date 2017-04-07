package com.frame.utils.encrypt;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * author yefeng
 * time 2017/3/23 下午11:35
 */
public class RSAEntity implements Serializable {

    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 359923477685469753L;

	private PrivateKey privateKey;

    private PublicKey publicKey;

    public RSAEntity(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKeyStr() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String getPrivateKeyStr() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
}
