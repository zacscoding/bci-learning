package com.asm_sample.document.ch2;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.asm_sample.util.CustomLogger;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM5);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        CustomLogger.println(
                "visit(int version : {} , int access : {} , String name : {} , String signature : {} , String superName : {} , String[] interfaces : {})",
                new Object[] {version, access, name, signature, superName, interfaces});
        // System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {}

    public void visitOuterClass(String owner, String name, String desc) {}

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {}

    public void visitInnerClass(String name, String outerName, String innerName, int access) {

    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        CustomLogger.println(
                "visitField(int access : {} , String name : {} , String desc : {} , String signature : {} , Object value : {})",
                new Object[] {access, name, desc, signature, value});
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        CustomLogger.println(
                "visitMethod(int access : {} , String name : {} , String desc : {} , String signature : {}, String[] exceptions : {})",
                new Object[] {access, name, desc, signature, exceptions});
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("visitEnd()");
    }
}
