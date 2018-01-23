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

            Class<?> proxyClass = new ReturnCheckProxyMain().defineClass(ReturnCheckClass.class.getName(), bytes);
            Object inst = proxyClass.newInstance();
            // Method method = proxyClass.getMethod("getName", new Class<?>[]{int.class, String.class});
            Method m1 = proxyClass.getMethod("getInt", new Class<?>[]{int.class});
            m1.invoke(inst,1);
            Method m2 = proxyClass.getMethod("getBoolean", new Class<?>[]{int.class});
            m2.invoke(inst,1);
            Method m3 = proxyClass.getMethod("getString", new Class<?>[]{int.class});
            m3.invoke(inst,1);
            Method m4 = proxyClass.getMethod("getLong", new Class<?>[]{int.class});
            m4.invoke(inst,1);
            Method m5 = proxyClass.getMethod("getDouble", new Class<?>[]{int.class});
            m5.invoke(inst,1);
            Method m6 = proxyClass.getMethod("getFloat", new Class<?>[]{int.class});
            m6.invoke(inst,1);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
