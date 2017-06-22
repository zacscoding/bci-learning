package step1.readandwrite;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class ChangeSuperClassAssist {
	public Class changeSuperClass() {
		Class clazz = null;
		try {
			// CtClass의 컨테이너 해시테이블(키 : 클래스이름) 사용		
			ClassPool pool = ClassPool.getDefault();
			CtClass cc;
			cc = pool.get("step1.readandwrite.domain.Child");
			cc.setSuperclass(pool.get("step1.readandwrite.domain.SuperTwo"));			
			// 
			cc.writeFile();
			
			// 클래스 로드
			clazz = cc.toClass();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clazz;
	}
}
