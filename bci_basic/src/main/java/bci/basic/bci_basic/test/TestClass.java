package bci.basic.bci_basic.test;

import bci.basic.bci_basic.util.JavaCodeUtils;

public class TestClass {
	public void test() {		
		System.out.println("TestClass.test() is called");
	}
	
	public void insertTest() {
		JavaCodeUtils.out.print("TestClass.insertTest(), before try");
		JavaCodeUtils.displayCurrentThread();
		try {
			JavaCodeUtils.out.print("TestClass.insertTest(), after try");
			JavaCodeUtils.displayCurrentThread();			
			String s = "a";
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			JavaCodeUtils.out.print("TestClass.insertTest(), after exception");
			JavaCodeUtils.displayCurrentThread();
		}
		finally {
			JavaCodeUtils.out.print("TestClass.insertTest(), after finally");
			JavaCodeUtils.displayCurrentThread();
		}	

	}

}
