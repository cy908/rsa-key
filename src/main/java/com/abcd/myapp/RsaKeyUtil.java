package com.abcd.myapp;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * RSA密钥工具
 */
@Slf4j
public class RsaKeyUtil {

    public static final int[] KEY_SIZES = { 1024, 2048, 4096 };
    public static final int KEY_SIZE = 2048;
    public static final String PUBLIC_KEY = "PublicKey";
    public static final String PRIVATE_KEY = "PrivateKey";

    private static final String ALGORITHM = "RSA";
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * 初始化密钥 {@link #KEY_SIZE}
     * 
     * @return
     */
    public static Map<String, Key> initKey() {
        return initKey(KEY_SIZE);
    }

    /**
     * 初始化密钥
     * 
     * @param keysize
     * @return
     */
    public static Map<String, Key> initKey(int keysize) {
        if (Arrays.stream(KEY_SIZES).noneMatch(item -> item == keysize)) {
            log.error("密钥长度错误！");
            return null;
        }
        log.info("密钥长度：{}", keysize);
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGen.initialize(keysize);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Key> keyMap = new HashMap<>(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取公钥
     * 
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Key> keyMap) {
        if (keyMap == null) {
            return null;
        }
        Key key = keyMap.get(PUBLIC_KEY);
        if (key == null) {
            return null;
        }
        return new String(Base64.getEncoder().encode(key.getEncoded()), UTF8);
    }

    /**
     * 获取私钥
     * 
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Key> keyMap) {
        if (keyMap == null) {
            return null;
        }
        Key key = keyMap.get(PRIVATE_KEY);
        if (key == null) {
            return null;
        }
        return new String(Base64.getEncoder().encode(key.getEncoded()), UTF8);
    }

}