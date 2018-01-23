package com.asm_sample;

import com.asm_sample.proxy.SampleTestClass;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.PrintWriter;

/**
 * @author zaccoding
 * github : https://github.com/zacscoding
 * @Date : 2018-01-23
 */
public class CheckClassAdapterTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleTestClass.class;
    }

    @Test
    public void test() throws Exception {
        ClassReader cr = new ClassReader(clazz.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = cw;
        PrintWriter pw = new PrintWriter(System.out);
        CheckClassAdapter.verify(cr, true, pw);
    }
}
