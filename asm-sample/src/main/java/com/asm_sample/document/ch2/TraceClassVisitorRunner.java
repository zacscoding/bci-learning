package com.asm_sample.document.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

public class TraceClassVisitorRunner {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cv, 0);
    }
}
