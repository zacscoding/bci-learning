package com.asm_sample.document.ch2;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class RemoveDebugAdapter extends ClassVisitor implements Opcodes {
    public RemoveDebugAdapter(ClassVisitor cv) {
        super(ASM5, cv);
    }
    
    @Override
    public void visitSource(String source, String debug) {
        
    }
    
    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        
    }
    
    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        
    }
}
