package com.asm_sample.agent.domain;

import com.asm_sample.agent.returncheck.ReturnCheckPrinter;
import com.asm_sample.util.CustomLogger;

/**
 * @author zaccoding
 * github : https://github.com/zacscoding
 * @Date : 2018-01-23
 */
public class TestClass {
    public int getLong(int idx) {
        int param = idx;
        CustomLogger.println("z", param);
        return 1;
    }
}
