## ASM !!!

#### Index

- <a href="https://github.com/zacscoding/bci-learning/tree/master/asm-sample/document"> Document </a>
- <a href="#use-tools"> Using tools </a>
- <a href="#get-return-value"> Get return value </a>


<div id="use-tools"></div>

#### Using tools

#### Index

- <a href="#signature"> Check Method signature </a>
- <a href="#ASMifer"> tools : ASMifer </a>
- <a href="#TraceClassVisitor"> tools : TraceClassVisitor </a>

<div id="signature"> </div>

#### Method Signature

> SamplteTestClass.java  

```
public class SampleTestClass {
    public String getName(String name, int age) {
        String info = name + "(" + age + ")";
        return name;
    }

    public String notProxyMethods() {
        return "not proxy methods";
    }
}
```

> SignatureTest.java

```
package com.asm_sample;

import com.asm_sample.proxy.SampleTestClass;
import com.asm_sample.util.CustomLogger;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.*;

/**
 * @author zacconding
 * @Date 2018-01-20
 * @GitHub : https://github.com/zacscoding
 */
public class SignatureTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleTestClass.class;
    }

    @Test
    public void display() throws Exception {
        ClassReader cr = new ClassReader(clazz.getResourceAsStream(clazz.getSimpleName() + ".class"));
        cr.accept(new ClassVisitor(Opcodes.ASM5) {
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                CustomLogger.println("## check {} methods", name);
                CustomLogger.println("## access : {} , name : {} , desc : {} , signature : {} , exceptions", access, name, desc, signature, exceptions);
                CustomLogger.println("## args types : {} , return type : {} \n", Type.getReturnType(desc), Type.getArgumentTypes(desc));
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }
}
```

> Result  

```
<init>  ::  ()V
getName  ::  (Ljava/lang/String;I)Ljava/lang/String;
notProxyMethods  ::  ()Ljava/lang/String;
```

---

<div id="ASMifer"></div>

#### ASMifer

> SampleTestClass  

```
public class SampleTestClass {
    public String getName() {
        String name  = "zaccoding";
        return name;
    }
}
```

> ASMifierTest

```
package com.asm_sample;

import com.asm_sample.proxy.SampleTestClass;
import org.junit.Before;
import org.junit.Test;

public class ASMifierTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleTestClass.class;
    }

    @Test
    public void display() throws Exception {
        String fullPath = clazz.getResource(clazz.getSimpleName() + ".class").getPath();
        org.objectweb.asm.util.ASMifier.main(new String[]{fullPath});
    }
}
```

> Result
```
package asm.com.asm_sample.proxy;
import java.util.*;
import org.objectweb.asm.*;
public class SampleTestClassDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter cw = new ClassWriter(0);
FieldVisitor fv;
MethodVisitor mv;
AnnotationVisitor av0;

cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "com/asm_sample/proxy/SampleTestClass", null, "java/lang/Object", null);

{
mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
mv.visitCode();
mv.visitVarInsn(ALOAD, 0);
mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
mv.visitInsn(RETURN);
mv.visitMaxs(1, 1);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
mv.visitCode();
mv.visitLdcInsn("zaccoding");
mv.visitVarInsn(ASTORE, 1);
mv.visitVarInsn(ALOAD, 1);
mv.visitInsn(ARETURN);
mv.visitMaxs(1, 2);
mv.visitEnd();
}
cw.visitEnd();

return cw.toByteArray();
}
}
```

---


<div id="TraceClassVisitor"></div>

#### TraceClassVisitor

> TraceClassVisitorTest.java

```
package com.asm_sample;

import com.asm_sample.proxy.SampleTestClass;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class TraceClassVisitorTest {
    Class<?> clazz;
    @Before
    public void setUp() {
        clazz = SampleTestClass.class;
    }

    @Test
    public void display() throws Exception {
        ClassReader cr = new ClassReader(clazz.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = cw;
        cv = new TraceClassVisitor(cv, new PrintWriter(System.out));
        cr.accept(cv,0);
    }
}
```

> Result  

```
// class version 49.0 (49)
// access flags 0x21
public class com/asm_sample/proxy/SampleTestClass {

  // compiled from: SampleTestClass.java

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 8 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lcom/asm_sample/proxy/SampleTestClass; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x1
  public getName()Ljava/lang/String;
   L0
    LINENUMBER 10 L0
    LDC "zaccoding"
    ASTORE 1
   L1
    LINENUMBER 11 L1
    ALOAD 1
    ARETURN
   L2
    LOCALVARIABLE this Lcom/asm_sample/proxy/SampleTestClass; L0 L2 0
    LOCALVARIABLE name Ljava/lang/String; L1 L2 1
    MAXSTACK = 1
    MAXLOCALS = 2
}
```

---

<div id="get-return-value"></div>

#### Get return value!

[see source code](https://github.com/zacscoding/bci-learning/tree/master/asm-sample/src/main/java/com/asm_sample/agent/returncheck)


> Target class :: ReturnCheckClass.java

```
public class ReturnCheckClass {
    public String getName(int param1, String param2) {
        return param2 + param1;
    }
}
```

> Proxy :: ReturnCheckClass.java

```
public class ReturnCheckClass {
    public String getName(int param1, String param2) {
        int local1 = param1;
        String local2 = param2;
        ReturnCheckPrinter.displayLocalVariable("[check local variable]", param1, param2);
        return param2 + param1;
        // copy return value & get local1, local2
        //ReturnCheckPrinter.displayReturnAndParam((param2 + param1), local1, local2);
    }
}
```

> Result :: new ReturnCheckClass().getName(1,"test");

```
## [ReturnCheckPrinter::displayLocalVariable is called] checkName : [check local variable], local1 : 1, local2 : test
## [ReturnCheckPrinter::displayReturnAndParam is called] returnValue : test1 , arg1 : 1, arg2 : test
```

> ASM CODE!

1. Apply visitCode() in MethodVisitor

```
class ReturnCheckClassProxyMV extends LocalVariablesSorter implements Opcodes {    
    private int firstParamIdx, secondParamIdx;
    ...
    @Override
    public void visitCode() {
        // int firstParamIdxLocalVariable = first arg
        mv.visitVarInsn(ILOAD, 1);
        firstParamIdx = newLocal(Type.getType(int.class));
        mv.visitVarInsn(ISTORE, firstParamIdx);

        // String secondParamIdxLocalVariable = second arg
        mv.visitVarInsn(ALOAD, 2);
        secondParamIdx = newLocal(Type.getType(String.class));
        mv.visitVarInsn(ASTORE, secondParamIdx);

        // check local variable
        mv.visitLdcInsn("[check local variable]");
        mv.visitVarInsn(ILOAD, 1);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/asm_sample/agent/returncheck/ReturnCheckPrinter", "displayLocalVariable", "(Ljava/lang/String;ILjava/lang/String;)V", false);
        mv.visitCode();
    }
    ...
}
```

2. Apply visitlnsn(int opcode)

```
class ReturnCheckClassProxyMV extends LocalVariablesSorter implements Opcodes {
    private int firstParamIdx, secondParamIdx;
    ...

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN)) {
            // pop & copy from stack == Return Object
            mv.visitInsn(Opcodes.DUP);
            // first local variable
            mv.visitVarInsn(Opcodes.ILOAD, firstParamIdx);
            // second local variable
            mv.visitVarInsn(Opcodes.ALOAD, secondParamIdx);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/asm_sample/agent/returncheck/ReturnCheckPrinter", "displayReturnAndParam", "(Ljava/lang/String;ILjava/lang/String;)V", false);
        }
        mv.visitInsn(opcode);
    }
    ...

    // compare by using org.objectweb.asm.util.ASMifier class
    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack +1, maxLocals + 2);
    }
}    
```
