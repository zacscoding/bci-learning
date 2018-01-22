package com.asm_sample.agent.returncheck;

import com.asm_sample.util.CustomLogger;

/**
 * @author zacconding
 * @Date 2018-01-22
 * @GitHub : https://github.com/zacscoding
 */
public class ReturnCheckPrinter {
    // (Ljava/lang/String;ILjava/lang/String;)V
    public static void displayReturnAndParam(String returnValue, int local1, String local2) {
        CustomLogger.println("## [ReturnCheckPrinter::displayReturnAndParam is called] returnValue : {} , arg1 : {}, arg2 : {}", returnValue, local1, local2);
    }

    // (Ljava/lang/String;ILjava/lang/String;)V
    public static void displayLocalVariable(String checkName, int arg1, String arg2) {
        CustomLogger.println("## [ReturnCheckPrinter::displayLocalVariable is called] checkName : {}, local1 : {}, local2 : {}", checkName, arg1, arg2);
    }
}
