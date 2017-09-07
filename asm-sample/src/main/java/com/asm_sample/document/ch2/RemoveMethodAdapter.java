package com.asm_sample.document.ch2;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RemoveMethodAdapter extends ClassVisitor implements Opcodes {
    private String mName;
    private String mDesc;  
    
    public RemoveMethodAdapter(ClassVisitor cv, String mName, String mDesc) {
        super(ASM5, cv);
        this.mName = mName;
        this.mDesc = mDesc;
    }
    
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(name.equals(mName) && desc.equals(mDesc)) {
            // do not delete to next visitor 
            return null;
        }
        
        return cv.visitMethod(access, name, desc, signature, exceptions);
    }
}
