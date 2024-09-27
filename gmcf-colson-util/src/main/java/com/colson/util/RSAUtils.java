package com.colson.util;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author liaozhiyue
 * 11/22/21 11:17 AM
 */
public class RSAUtils {

    private static final String ALGORITHM = "RSA";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    private static final int KEY_SIZE = 1024;

    private static final Charset CHARSET = StandardCharsets.UTF_8;


    /**
     * 用于生成公钥和私钥对，基于RSA算法生成对象
     *
     * @throws NoSuchAlgorithmException exception
     */
    public static RSAKeyPair genKeyPair() throws Exception {

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());

        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        System.out.println("publicKey:"+publicKeyString);
        System.out.println("privateKey:"+privateKeyString);
        return new RSAKeyPair(privateKeyString, publicKeyString);
    }

    public static void main(String[] args) throws Exception {
        genKeyPair();
    }

    /**
     * 加密
     *
     * @param content   明文
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception exception
     */
    public static String encrypt(String content, String publicKey) throws Exception {

        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);

        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));

        //RSA加密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        return Base64.encodeBase64String(cipher.doFinal(content.getBytes(CHARSET)));

    }

    /**
     * 解密
     *
     * @param content    密文
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception exception
     */
    public static String decrypt(String content, String privateKey) throws Exception {
        Key key = getPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(content.getBytes());
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    @Data
    public static class RSAKeyPair {

        private String priKey;
        private String pubKey;

        public RSAKeyPair(String priKey, String pubKey) {
            this.priKey = priKey;
            this.pubKey = pubKey;
        }

    }


}
