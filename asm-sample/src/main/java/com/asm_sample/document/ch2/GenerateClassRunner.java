package com.asm_sample.document.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.asm_sample.util.CustomLogger;

public class GenerateClassRunner extends ClassLoader {
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        byte[] bytes = GenerateClass.generateComparableInterface();
        Class<?> comparableClazz = new GenerateClassRunner().defineClass("com.asm_sample.document.ch2.Comparable", bytes);
        Field[] fields = comparableClazz.getFields();
        
        for(Field field : fields) {
            CustomLogger.println("## [field] :: name : {} , value : {}", new Object[]{field.getName(), field.get(null)});
        }
        
        Method[] methods = comparableClazz.getDeclaredMethods();
        for(Method method : methods) {
            CustomLogger.println("## [method] :: name : {}",new Object[]{method.getName()});
        }
    }
    
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }    
}
