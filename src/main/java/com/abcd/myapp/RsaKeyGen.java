package com.abcd.myapp;

import java.security.Key;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RsaKeyGen {

    private static final String PUBLIC_PATH = "public.txt";
    private static final String PRIVATE_PATH = "private.txt";

    @Autowired
    private Config config;

    public void run() {
        Integer size = config.getSize();
        Map<String, Key> keyMap = null;
        if (size == null) {
            keyMap = RsaKeyUtil.initKey();
        } else {
            keyMap = RsaKeyUtil.initKey(size.intValue());
        }
        String publicKey = RsaKeyUtil.getPublicKey(keyMap);
        String privateKey = RsaKeyUtil.getPrivateKey(keyMap);
        FileUtil.wirte(PUBLIC_PATH, publicKey);
        FileUtil.wirte(PRIVATE_PATH, privateKey);
        log.info("公钥已写入：{}", PUBLIC_PATH);
        log.info("私钥已写入：{}", PRIVATE_PATH);
    }

}