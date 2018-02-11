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
    public String getString(int idx) {
        System.out.println("## ReturnCheckClass::getString() is called");
        return "getStringResultValue@!";
    }

    public int getInt(int idx) {
        System.out.println("## ReturnCheckClass::getInt() is called");
        return 1;
    }

    public float getFloat(int idx) {
        System.out.println("## ReturnCheckClass::getFloat() is called");
        return 1.1F;
    }

    public boolean getBoolean(int idx) {
        System.out.println("## ReturnCheckClass::getBoolean() is called");
        return true;
    }

    public long getLong(int idx) {
        System.out.println("## ReturnCheckClass::getLong() is called");
        return 2L;
    }

    public double getDouble(int idx) {
        System.out.println("## ReturnCheckClass::getDouble() is called");
        return 2F;
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