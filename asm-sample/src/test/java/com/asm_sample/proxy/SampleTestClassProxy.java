package com.asm_sample.proxy;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class SampleTestClassProxy {
    public String getName(String name, int age) {
        System.out.println("SimpleTestClass::getName called");
        String info = name + "(" + age + ")";
        System.out.println("end method");
        return name;
    }
}
