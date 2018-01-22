package com.asm_sample.agent.returncheck;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Method;

/**
 * @author zacconding
 * @Date 2018-01-22
 * @GitHub : https://github.com/zacscoding
 */
public class ReturnCheckProxyMain extends ClassLoader {
    public static void main(String[] args) {
        try {
            ClassLoader cl = ReturnCheckProxyMain.class.getClassLoader();
            ClassReader cr = new ClassReader(cl.getResourceAsStream(ReturnCheckClass.class.getName().replace('.','/') + ".class"));
            final ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS);
            cr.accept(new ReturnChecClassProxy(cw), ClassReader.EXPAND_FRAMES);
            byte[] bytes = cw.toByteArray();

//            ClassWriter cw2 = new ClassWriter(0);
//            TraceClassVisitor cv2 = new TraceClassVisitor(cw2, new PrintWriter(System.out));
//            ClassReader cr2 = new ClassReader(cl.getResourceAsStream(ReturnCheckClass.class.getName().replace('.','/') + ".class"));
//            cr.accept(cv2, 0);

            Class<?> proxyClass = new ReturnCheckProxyMain().defineClass(ReturnCheckClass.class.getName(), bytes);
            Object inst = proxyClass.newInstance();
            Method method = proxyClass.getMethod("getName", new Class<?>[]{int.class, String.class});
            String result = (String)method.invoke(inst, 1, "test");
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
