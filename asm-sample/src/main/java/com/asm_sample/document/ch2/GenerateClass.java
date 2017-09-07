package com.asm_sample.document.ch2;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class GenerateClass implements Opcodes {
    public static byte[] generateComparableInterface() {
        ClassWriter cw = new ClassWriter(0);
        
        // cw.visit(version, access, name, signature, superName, interfaces);
        cw.visit(V1_6, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "com/asm_sample/document/ch2/Comparable", null, "java/lang/Object", new String[]{"com/asm_sample/document/ch2/Mesurable"});
        
        // cw.visitField(access, name, desc, signature, value)
        // annotatoin이 없으므로, visitEnd() 호출
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        
        // cw.visitMethod(access, name, desc, signature, exceptions)
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }
}
