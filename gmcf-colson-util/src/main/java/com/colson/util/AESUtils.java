package com.colson.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


/**
 * @author liaozhiyue
 * 11/21/21 5:14 PM
 */
@Slf4j
public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";

    // 算法/模式/补码方式
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final Charset CHARSET = StandardCharsets.UTF_8;


    public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String genKey() {
        return generateString(16);
    }

    /**
     * 加密
     *
     * @param content 明文
     * @param key     密码
     * @return 密文
     */
    public static String encrypt(String content, String key) {
        try {
            byte[] encrypted = encrypt2bytes(content, key);
            if (encrypted == null) {
                return null;
            }
            return Base64Utils.encodeToString(encrypted);
        } catch (Exception e) {
            log.error("failed to encrypt: {} of {}", content, e);
            return null;
        }
    }

    /**
     * 加密
     *
     * @param content 明文
     * @param key     密码
     * @return 密文
     */
    public static byte[] encrypt2bytes(String content, String key) {
        try {
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(content.getBytes(CHARSET));
        } catch (Exception e) {
            log.error("failed to encrypt: {} of {}", content, e);
            return null;
        }
    }

    /**
     * 解密
     *
     * @param content 密文
     * @param key     密码
     * @return 明文
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static String decrypt(String content, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        return decrypt(Base64Utils.decodeFromString(content), key);
    }

    /**
     * @param content 密文
     * @param key     密码
     * @return 明文
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static String decrypt(byte[] content, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        if (key == null) {
            log.error("AES key should not be null");
            return null;
        }

        byte[] raw = key.getBytes(CHARSET);
        SecretKeySpec keySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        try {
            byte[] original = cipher.doFinal(content);
            return new String(original, CHARSET);
        } catch (Exception e) {
            log.error("failed to decrypt content: {}/ key: {}, e: {}", content, key, e);
            return null;
        }
    }

    private static String generateString(int length) {
        Random random = new SecureRandom();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
}
