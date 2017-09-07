package com.asm_sample.document.ch2;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.asm_sample.util.CustomLogger;

public class Optimize {
    public static void main(String[] args) {
        // first transform
        byte[] b1 = GenerateClass.generateComparableInterface();
        long time = -System.nanoTime();
        ClassWriter cw = new ClassWriter(0);
        // cv forwards all events to cw
        ClassVisitor cv = new ChangeVersionAdapter(cw);
        ClassReader cr = new ClassReader(b1);
        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray();
        time += System.nanoTime();
        CustomLogger.println("## [first transform] :: {} ms", time);

        // optimized transform
        byte[] b3 = GenerateClass.generateComparableInterface();
        time = -System.nanoTime();
        ClassReader cr2 = new ClassReader(b3);
        ClassWriter cw2 = new ClassWriter(cr, 0);
        ChangeVersionAdapter ca = new ChangeVersionAdapter(cw2);
        cr2.accept(ca, 0);
        byte[] b4 = cw.toByteArray();
        time += System.nanoTime();
        CustomLogger.println("## [optimized transform] :: {} ms", time);
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader l, String name, Class c, ProtectionDomain d,
                    byte[] b) throws IllegalClassFormatException {
                ClassReader cr = new ClassReader(b);
                ClassWriter cw = new ClassWriter(cr, 0);
                ClassVisitor cv = new ChangeVersionAdapter(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            }
        });
    }
}
