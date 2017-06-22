package bci.basic.bci_basic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import step1.readandwrite.ChangeSuperClassAssist;
import step1.readandwrite.MakeClassAssist;
import step1.readandwrite.domain.Child;

public class StepOneTest {
	
	// =================================
	// Test change to super class
	// =================================
	@Test
	public void changeSuperClass() throws Exception {
		Child child = (Child)(new ChangeSuperClassAssist().changeSuperClass().newInstance());        
    	assertThat(child.displayClassName(),is("SuperTwo"));
	}
	
	
	// =================================
	// Fail!
	// Test create dynamic construtors, methods
	// =================================
	@Test
	public void makeDynamicMethods() throws Exception {
		//new MakeClassAssist().makeClass().newInstance();		
	}
	
	
	
	

}
