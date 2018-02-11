package com.asm_sample.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author zacconding
 * @Date 2018-02-11
 * @GitHub : https://github.com/zacscoding
 */
public class WriteClassFileUitl {

    private static final String root = "D:\\classes";

    /**
     * Write class file & view java code by using decompiler
     */
    public static void writeByteCode(byte[] bytes, String className) {
        if (bytes == null || className == null) {
            return;
        }

        className = className.replace('.', File.separatorChar).replace('/', File.separatorChar);
        File dir = null;

        String dirPath = root;
        int classDot = className.lastIndexOf(File.separatorChar);
        if (classDot > -1) {
            dirPath += (File.separator + className.substring(0, classDot));
        }

        dir = new File(dirPath);
        if (!dir.canWrite()) {
            dir.mkdirs();
        }

        if (!dir.canWrite()) {
            System.out.println("Can`t write : " + dirPath);
            return;
        }

        String clazz = className.substring(classDot + 1) + ".class";
        try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(new File(dir, clazz)))) {
            buffOut.write(bytes);
            buffOut.flush();
            buffOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[1];
        bytes[0] = 1;
        String[] classNames = {
            "org/test/Test",
            "com.test.Test2"
        };

        for (String className : classNames) {
            writeByteCode(bytes, className);
        }
    }
}
