package com.asm_sample.document.ch2;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class ClassPrinterRunner {
    public static void main(String[] args) throws IOException {
        displayClass("java.lang.Runnable");
        displayClass("com.asm_sample.document.ch2.domain.TestClass");
    }

    public static void displayClass(String className) throws IOException {
        System.out.println("------------------- display  ::  " + className + "---------------------");
        ClassPrinter cp = new ClassPrinter();
        // ClassReader cr = new ClassReader("java.lang.Runnable");
        // ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ClassLoader cl = ClassPrinterRunner.class.getClassLoader();
        ClassReader cr = new ClassReader(cl.getResourceAsStream(className.replace('.', '/') + ".class"));
        cr.accept(cp, 0);
        System.out.println("------------------- end of  ::  " + className + "---------------------");
    }

}
