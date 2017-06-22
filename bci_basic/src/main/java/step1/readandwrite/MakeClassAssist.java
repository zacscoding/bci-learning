package step1.readandwrite;

import bci.basic.bci_basic.util.StringUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewMethod;

public class MakeClassAssist {
	public MakeClassAssist() {
		
	}

	public Class makeClass() throws Exception {
		ClassPool pool = ClassPool.getDefault();

		// make class
		CtClass newConsTarget = pool.makeClass("DynamicPrinter");
		
		// constructor
		CtConstructor cons = new CtConstructor(null, newConsTarget);		
		cons.setBody( StringUtils.getPrintln("dynamic constructor!") );
		newConsTarget.addConstructor(cons);
		

		// method
		CtClass newMethodTarget = pool.get("step1.readandwrite.MakeClassAssist");
		CtNewMethod newMethod = new CtNewMethod();
		String newMethodValue = "public void invokeDynamicClass() { "
							+ "DynamicPrinter dp = new DynamicPrinter(); }";
		newMethod.make(newMethodValue,newMethodTarget);		
		
		CtConstructor[] constructors = newMethodTarget.getConstructors();
		for( CtConstructor cc : constructors ) {
			if( cc.getParameterTypes() == null ) {
				cc.insertBefore("this.invokeDynamicClass();");
			}
		}
		
		return newMethodTarget.toClass();
	}
	
}
