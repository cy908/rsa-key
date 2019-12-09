package com.abcd.myapp;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件工具
 */
@Slf4j
public class FileUtil {

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * 写文件
     */
    public static void wirte(String path, String content) {
        if (path == null || content == null) {
            return;
        }
        try {
            Files.write(Paths.get(path), content.getBytes(UTF8));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}