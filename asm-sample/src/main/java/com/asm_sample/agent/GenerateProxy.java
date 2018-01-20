package com.asm_sample.agent;

import com.asm_sample.util.CustomLogger;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.LocalVariablesSorter;

import java.util.Arrays;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class GenerateProxy extends ClassVisitor implements Opcodes {
    public GenerateProxy(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        CustomLogger.println("access : {} , name : {} , desc : {}, signature : {} , exceptions : {}", access, name ,desc, signature, Arrays.toString(exceptions));
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if(mv == null) {
            return mv;
        }

        if("getName".equals(name) && "(Ljava/lang/String;I)Ljava/lang/String;".equals(desc)) {
            return new GenerateProxyMethodVisitor(access, desc, mv);
        }

        return mv;
    }
}

class GenerateProxyMethodVisitor extends LocalVariablesSorter implements Opcodes {
    Label finallyLabel = new Label();
    protected GenerateProxyMethodVisitor(int access, String desc, MethodVisitor mv) {
        super(ASM5, access, desc, mv);
    }

    @Override
    public void visitCode() {
        System.out.println("visitCode() is called");
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("## SimpleTestClass::getName called");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitLabel(finallyLabel);
        mv.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        CustomLogger.println("visitInsn() is called ");
        if ((opcode >= IRETURN && opcode <= RETURN)) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("## SimpleTestClass::getName is ended in visitLnsn");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }
}
