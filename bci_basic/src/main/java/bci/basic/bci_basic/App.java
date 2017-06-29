package bci.basic.bci_basic;

import bci.basic.bci_basic.test.LearningTest;
import step1.readandwrite.ChangeSuperClassAssist;
import step1.readandwrite.domain.Child;

public class App {
    public static void main( String[] args ) {
        try {        	
        	// =================================
        	// Test change to super class
        	// =================================
        	// changeSuperClass();
        	
        	// =================================
        	// Test Api
        	// =================================
        	learningApi();
        	
        } catch(Exception e) {
        	e.printStackTrace();
        	System.exit(1);
        }
    }
    
    public static void changeSuperClass() throws Exception {    	
    	Child child = (Child)(new ChangeSuperClassAssist().changeSuperClass().newInstance());        
    	child.displayClassName();
    }
    
    public static void learningApi() throws Exception {
    	new LearningTest().ApiTest();
    }
}
