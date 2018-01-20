package com.asm_sample;

import com.asm_sample.agent.domain.SampleClass;
import com.asm_sample.proxy.SampleTestClass;
import com.asm_sample.util.CustomLogger;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.*;

import java.util.Arrays;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class SignatureTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleClass.class;
    }

    @Test
    public void display() throws Exception {
        ClassReader cr = new ClassReader(clazz.getResourceAsStream(clazz.getSimpleName() + ".class"));
        cr.accept(new ClassVisitor(Opcodes.ASM5) {
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                CustomLogger.println("## check {} methods", name);
                CustomLogger.println("## access : {} , name : {} , desc : {} , signature : {} , exceptions", access, name, desc, signature, Arrays.toString(exceptions));
                CustomLogger.println("## args types : {} , return type : {} \n", Arrays.toString(Type.getArgumentTypes(desc)), Type.getReturnType(desc));
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }
}
