package com.asm_sample.agent.returncheck;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

/**
 * @author zacconding
 * @Date 2018-01-22
 * @GitHub : https://github.com/zacscoding
 */
public class ReturnChecClassProxy extends ClassVisitor implements Opcodes {
    public ReturnChecClassProxy(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if(mv == null) {
            return mv;
        }

        if("getName".equals(name) && "(ILjava/lang/String;)Ljava/lang/String;".equals(desc)) {
            System.out.println("## find target");
            return new ReturnCheckClassProxyMV(access, desc, mv);
        }
        return mv;
    }
}

class ReturnCheckClassProxyMV extends LocalVariablesSorter implements Opcodes {
    private int firstParamIdx, secondParamIdx;
    protected ReturnCheckClassProxyMV(int access, String desc, MethodVisitor mv) {
        super(ASM5, access, desc, mv);
    }

    @Override
    public void visitCode() {
        // int firstParamIdxLocalVariable = first arg
        mv.visitVarInsn(ILOAD, 1);
        firstParamIdx = newLocal(Type.getType(int.class));
        mv.visitVarInsn(ISTORE, firstParamIdx);

        // String secondParamIdxLocalVariable = second arg
        mv.visitVarInsn(ALOAD, 2);
        secondParamIdx = newLocal(Type.getType(String.class));
        mv.visitVarInsn(ASTORE, secondParamIdx);

        // check local variable
        mv.visitLdcInsn("[check local variable]");
        mv.visitVarInsn(ILOAD, 1);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/asm_sample/agent/returncheck/ReturnCheckPrinter", "displayLocalVariable", "(Ljava/lang/String;ILjava/lang/String;)V", false);
        mv.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN)) {
            // pop & copy from stack == Return Object
            mv.visitInsn(Opcodes.DUP);
            // first local variable
            mv.visitVarInsn(Opcodes.ILOAD, firstParamIdx);
            // second local variable
            mv.visitVarInsn(Opcodes.ALOAD, secondParamIdx);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/asm_sample/agent/returncheck/ReturnCheckPrinter", "displayReturnAndParam", "(Ljava/lang/String;ILjava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack +1, maxLocals + 2);
    }
}


