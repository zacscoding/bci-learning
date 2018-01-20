package com.asm_sample.agent.domain;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class SampleClass {
    public String getName(String name, int age) {
        String info = name + "(" + age + ")";
        return info;
    }

    public String notProxyMethods() {
        return "not proxy methods";
    }
}
