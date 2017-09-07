package com.asm_sample.util;

import java.io.PrintStream;

public abstract class CustomLogger {
    private static PrintStream PS = System.out;

    public static void println(String content) {
        PS.println(content);
    }

    public static void println(String content, Object... args) {
        if (args == null || args.length == 0) {
            PS.println(content);
        }

        PS.println(parseContent(content, args));
    }

    private static String parseContent(String content, Object[] args) {
        StringBuilder sb = new StringBuilder();
        int argIdx = 0;
        int length = content.length();
        for (int i = 0; i < length; i++) {
            char curChar = content.charAt(i);
            if (i != length -1 && content.charAt(i) == '{' && (content.charAt(i + 1) == '}')) {
                sb.append(args[argIdx++]);
                i += 1;
            }            
            else {
                sb.append(curChar);
            }
        }
        
        return sb.toString();
    }
}
