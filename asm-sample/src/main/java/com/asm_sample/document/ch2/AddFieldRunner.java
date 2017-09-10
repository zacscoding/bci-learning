package com.asm_sample.document.ch2;

import java.io.IOException;
import java.lang.reflect.Field;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.asm_sample.util.CustomLogger;

public class AddFieldRunner extends ClassLoader {    
    public static void main(String[] args) throws IOException {
        /*  add field member    */
        String className = "com.asm_sample.document.ch2.domain.TestClass";
        
        ClassLoader loader = AddFieldAdatper.class.getClassLoader();
        ClassReader cr = new ClassReader(loader.getResourceAsStream(className.replace('.', '/') + ".class"));
        ClassWriter cw = new ClassWriter(cr, 0);
        AddFieldAdatper addFieldAdapter = new AddFieldAdatper(cw, Opcodes.ACC_PRIVATE, "addedField","Ljava/lang/String;");
        cr.accept(addFieldAdapter, 0);
        byte[] bytes = cw.toByteArray();
        
        /*  check   */
        Class<?> clazz = new AddFieldRunner().defineClass(className, bytes, 0, bytes.length);
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            CustomLogger.println("## name : {} , type : {}", field.getName() , field.getType());
        }
    }
}
