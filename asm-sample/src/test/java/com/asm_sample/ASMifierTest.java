package com.asm_sample;

import com.asm_sample.proxy.SampleTestClass;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class ASMifierTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleTestClass.class;
        // clazz = SampleClassProxy.class;
        // clazz = SampleClass.class;
        // clazz = TryCatchSample.class;
        // clazz = HookPrinter.class;
        // clazz = ReturnCheckPrinter.class;
        // clazz = TestClass.class;
    }

    @Test
    public void display() throws Exception {
        String fullPath = clazz.getResource(clazz.getSimpleName() + ".class").getPath();
        org.objectweb.asm.util.ASMifier.main(new String[]{fullPath});
    }
}
