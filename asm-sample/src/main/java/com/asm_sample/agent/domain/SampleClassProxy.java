package com.asm_sample.agent.domain;

/**
 * @author zacconding
 * @Date 2018-01-21
 * @GitHub : https://github.com/zacscoding
 */
public class SampleClassProxy {
    public String getName(String name, int age, boolean exception) {
        System.out.println("## SampleClass::getName() is called");
        /*  ORIGINAL CODE */
        String info = name + "(" + age + ")";
        /*  ORIGINAL CODE */
        System.out.println("## SampleClass::getName() is ended");
        return info;
    }
}
