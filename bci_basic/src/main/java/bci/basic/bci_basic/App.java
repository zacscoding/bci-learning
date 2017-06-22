package bci.basic.bci_basic;

import step1.readandwrite.ChangeSuperClassAssist;
import step1.readandwrite.domain.Child;

public class App {
    public static void main( String[] args ) throws Exception {
        try {        	
        	// =================================
        	// Test change to super class
        	// =================================
        	changeSuperClass();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void changeSuperClass() throws Exception {    	
    	Child child = (Child)(new ChangeSuperClassAssist().changeSuperClass().newInstance());        
    	child.displayClassName();
    }
}
