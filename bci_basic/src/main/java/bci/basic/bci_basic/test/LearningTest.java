package bci.basic.bci_basic.test;

import bci.basic.bci_basic.util.JavaCodeUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import step1.readandwrite.domain.TestDomain;

public class LearningTest {
	
	public void ApiTest() throws Exception {
		//parameterTest();
		
		insertTest();
	}
	
	
	//////////////////////////////////////////
	// jar test
	//////////////////////////////////////////
	public void jarTest() {
		
	}
	
	
	//////////////////////////////////////////
	// insert method info
	//////////////////////////////////////////	
	public void insertTest() throws Exception {
		TestDomain vo = (TestDomain)(transformClass().newInstance());
		vo.setId(1);
		vo.setName("test");
		vo.testMethod("test", "hi", 1, 10L);
	}
	
	public Class transformClass() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.get("step1.readandwrite.domain.TestDomain");
		
		CtMethod[] ctMethods = ctClass.getDeclaredMethods();		
		for( CtMethod ctMethod : ctMethods ) {
			System.out.println("check : " + ctMethod.getName());
			if( checkMethod(ctMethod) ) {
				System.out.println("## insert method : " + ctMethod.getName());
				insertCode(ctMethod);
			}			
		}		
		
		ctClass.writeFile();
		return ctClass.toClass();
	}
	
	private boolean checkMethod(CtMethod ctMethod) {
		return (!ctMethod.isEmpty())&&(!Modifier.isNative(ctMethod.getModifiers()));
	}
	
	private void insertCode(CtMethod ctMethod) throws Exception {
		String dynamicCode = JavaCodeUtils.getPrintlnCode("-- " + ctMethod.getLongName() + " is called --");
		
		String[] paramNames = getParameterNames(ctMethod);
		
		if( paramNames != null ) {
			for( int i=1; i<=paramNames.length;i++ ) {
				dynamicCode += JavaCodeUtils.getPrintCode( paramNames[i-1]+ " : " );
				String toStringCode = "System.out.println(" + paramNames[i-1] + ".toString());";
				dynamicCode	+= "System.out.println("+paramNames[i-1] +");";				
			}
		}		
		
		ctMethod.insertBefore(dynamicCode);	
		//System.out.println("inserted code ");
		//System.out.println(dynamicCode);
		ctMethod.insertAfter(JavaCodeUtils.getPrintlnCode("--------------------------------------------------------------"));
	}
	
	private String[] getParameterNames(CtMethod ctMethod) throws NotFoundException {
		int paramSize = ctMethod.getParameterTypes().length;
		if( paramSize == 0 ) {
			return null;
		}
		
		String[] paramNames = new String[paramSize]; 
		MethodInfo methodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();			
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
		
		for (int i = 0; i < paramSize; i++) {
			paramNames[i] = attr.variableName(i + pos);
		}
		
		return paramNames;
	}
	
	
	//////////////////////////////////////////
	// display parameter test 
	//////////////////////////////////////////
	public void parameterTest() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("step1.readandwrite.domain.TestDomain");
		
		//CtMethod[] ctMethods = cc.getMethods();
		CtMethod[] ctMethods = cc.getDeclaredMethods();
		for(CtMethod ctMethod : ctMethods ) {			
			//System.out.println(ctMethod.getName());
			//if(!ctMethod.getName().equals("staticTestMethod")) continue;
			displayParameterNames(ctMethod,ctMethod.getParameterTypes().length);					
		}
	}
	
	public void displayParameterNames(CtMethod ctMethod,int parameterSize) {
		System.out.println("--------" + ctMethod.getLongName() + "-----------");
		MethodInfo methodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();			
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
		for (int i = 0; i < parameterSize; i++) {
			System.out.print(attr.variableName(i + pos) + ",");
		}		
		System.out.println("\n--------------------------------------------------------------\n\n");
		
	}

}
