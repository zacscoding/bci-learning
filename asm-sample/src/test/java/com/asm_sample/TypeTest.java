package com.asm_sample;

import jdk.internal.org.objectweb.asm.Type;
import org.junit.Test;

/**
 * @author zaccoding
 * github : https://github.com/zacscoding
 * @Date : 2018-01-23
 */
public class TypeTest {
    @Test
    public void test() {
        String desc = "(I)Ljava/lang/String;";
        Type returnType = Type.getReturnType(desc);
        System.out.println(returnType.getDescriptor());
    }
}
