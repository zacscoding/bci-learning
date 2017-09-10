package com.asm_sample.document.ch2;

import org.objectweb.asm.Type;

import com.asm_sample.util.CustomLogger;

public class TypeCheck {
    public static void main(String[] args) {        
        CustomLogger.println("Type.INT_TYPE : {}, Type.getType(String.class) : {}, Type.getType(String.class).getInternalName() : {} "
                ,new Object[]{Type.INT_TYPE, Type.getType(String.class), Type.getType(String.class).getInternalName()});
        
        CustomLogger.println("Type.getType(String.class).getDescriptor() : {}"
                , new Object[]{Type.getType(String.class).getDescriptor()});
    }
}
