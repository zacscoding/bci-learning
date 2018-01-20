package com.asm_sample.agent;

import com.asm_sample.agent.domain.SampleClass;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class GenerateProxyMain extends ClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader cl = GenerateProxyMain.class.getClassLoader();
        ClassReader cr = new ClassReader(cl.getResourceAsStream(SampleClass.class.getName().replace('.','/') + ".class"));
        final ClassWriter cw = new ClassWriter(cr,
                ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS);
        cr.accept(new GenerateProxy(cw), ClassReader.EXPAND_FRAMES);
        byte[] bytes = cw.toByteArray();

        Class<?> proxyClass = new GenerateProxyMain().defineClass(SampleClass.class.getName(), bytes);
        Object inst = proxyClass.newInstance();
        Method method = proxyClass.getMethod("getName", String.class, int.class);
        System.out.println(method.invoke(inst,"zac", 19));

        ClassWriter cw2 = new ClassWriter(0);
        TraceClassVisitor cv2 = new TraceClassVisitor(cw2, new PrintWriter(System.out));
        ClassReader cr2 = new ClassReader(SampleClass.class.getName());
        cr.accept(cv2, 0);
    }

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
