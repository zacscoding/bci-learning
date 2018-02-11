package com.asm_sample;

import org.junit.Ignore;
import org.junit.Test;
import org.objectweb.asm.Type;

/**
 * @author zaccoding github : https://github.com/zacscoding
 * @Date : 2018-01-23
 */
public class TypeTest {

    @Test
    @Ignore
    public void test() {
        String desc = "(I)Ljava/lang/String;";
        Type returnType = Type.getReturnType(desc);
        System.out.println(returnType.getDescriptor());
    }
}
