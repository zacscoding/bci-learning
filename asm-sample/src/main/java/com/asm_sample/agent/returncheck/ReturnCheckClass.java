package com.asm_sample.agent.returncheck;

/**
 * @author zacconding
 * @Date 2018-01-22
 * @GitHub : https://github.com/zacscoding
 */
public class ReturnCheckClass {
    public String getName(int param1, String param2) {
        return param2 + param1;
    }
}

/*
    public String getName(int param1, String param2) {
        int local1 = param1;
        String local2 = param2;
        ReturnCheckPrinter.displayLocalVariable("[check local variable]", param1, param2);
        return param2 + param1;
        //ReturnCheckPrinter.displayReturnAndParam((param2 + param1), local1, local2);
    }
 */