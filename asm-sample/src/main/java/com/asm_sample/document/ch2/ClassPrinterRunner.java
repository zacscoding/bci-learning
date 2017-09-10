package com.asm_sample.document.ch2;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class ClassPrinterRunner {
    public static void main(String[] args) throws IOException {
        String className = "java.lang.Runnable";
        
        ClassPrinter cp = new ClassPrinter();
        // ClassReader cr = new ClassReader("java.lang.Runnable");
        //ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ClassLoader cl = ClassPrinterRunner.class.getClassLoader();
        ClassReader cr = new ClassReader(cl.getResourceAsStream(className.replace('.','/') + ".class"));        
        cr.accept(cp, 0);        
    }    
}
