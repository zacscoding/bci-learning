package com.asm_sample.document.ch2;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class GenerateClassRunner implements Opcodes {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        
        // cw.visit(version, access, name, signature, superName, interfaces);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        
        // cw.visitField(access, name, desc, signature, value)
        cw.visitField(ACC_PUBLIC + ACC_FINAL, "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        
        // cw.visitMethod(access, name, desc, signature, exceptions)
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();

        cw.visitEnd();
        byte[] b = cw.toByteArray();
    }
}
