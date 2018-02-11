package com.asm_sample.proxy;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class SampleTestClass {
    public void setTest(int idx, String value) {

    }
    public String getName(String name, int age) {
        System.out.println(name);
        String info = name + "(" + age + ")";
        return name;
    }
    /*
    public String notProxyMethods() {
        return "not proxy methods";
    }
    */
}
