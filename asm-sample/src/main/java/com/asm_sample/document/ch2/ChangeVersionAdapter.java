package com.asm_sample.document.ch2;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ChangeVersionAdapter extends ClassVisitor implements Opcodes {
    public ChangeVersionAdapter(ClassVisitor cv) {
        super(ASM5, cv);
    }
    
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(V1_5, access, name, signature, superName, interfaces);        
        System.out.println("ChangeVersionAdapter::visit");
    }
}
