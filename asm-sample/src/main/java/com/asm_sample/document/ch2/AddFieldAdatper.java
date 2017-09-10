package com.asm_sample.document.ch2;

import java.io.IOException;
import java.lang.reflect.Field;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import com.asm_sample.document.ch2.domain.TestClass;
import com.asm_sample.util.CustomLogger;

public class AddFieldAdatper extends ClassVisitor implements Opcodes {
    private int fAcc;
    private String fName;
    private String fDesc;
    private boolean isFieldPresent;
    
    public AddFieldAdatper(ClassVisitor cv, int fAcc, String fName, String fDesc) {
        super(ASM5, cv);
        this.fAcc = fAcc;
        this.fName = fName;
        this.fDesc = fDesc;
    }
    
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        CustomLogger.println("## visitField() :: access :{}, name : {} , desc : {} , signature : {}, value : {}",
                new Object[]{access, name, desc, signature, value});
        // check duplicate field name
        if(name.equals(fName)) {
            System.out.println("exist field :: " + name);            
            isFieldPresent = true;
        }
        
        return cv.visitField(access, name, desc, signature, value);
    }
    
    @Override
    public void visitEnd() {
        if(!isFieldPresent) {
            FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, null, null);
            if(fv != null) {                
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
