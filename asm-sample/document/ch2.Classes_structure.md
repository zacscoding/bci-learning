# Classes

[document](http://download.forge.objectweb.org/asm/asm4-guide.pdf)

- <a href="#1">2.1.1 Overview</a>
- <a href="#2">2.1.2 Internal names</a>
- <a href="#3">2.1.3 Type descriptors</a>
- <a href="#4">2.1.4 Method descriptors</a>
- <a href="#5">2.2.1 Presentation</a>
- <a href="#6">2.2.2 Parsing classes</a>
- <a href="#7">2.2.3 Generating Classes</a>

## structure

### 2.1.1 Overview
<div id="1"></div>

> Overall structure of a compiled class (*means zero or more)

<table>
  <tr>
    <td colspan="2">
      Modifiers, name, super class, interfaces
    </td>
  </tr>
  <tr>
    <td colspan="2">
      Constant pool: numeric, string and type constants
    </td>
  </tr>
  <tr>
    <td colspan="2">
      Source file name (optional)
    </td>
  </tr>
  <tr>
    <td colspan="2">
      Enclosing class reference
    </td>
  </tr>
  <tr>
    <td colspan="2">
      Annotation*
    </td>
  </tr>
  <tr>
    <td colspan="2">
      Attribute*
    </td>
  </tr>
  <tr>
    <td>Inner class*</td>
    <td>Name</td>
  </tr>
  <tr>
    <td>Field*</td>
    <td>      
        Modifiers, name, type <br />
        Annotation*<br />
        Attribute*<br />      
    </td>
  </tr>
  <tr>
    <td>Method*</td>
    <td>
      Modifiers, name, return and parameter types <br />
      Annotation*<br />
      Attribute*<br />
      compiled code<br />
    </td>
  </tr>
</table>

---

### 2.1.2 Internal names
<div id="2"></div>

SuperClass or interfaces implemented by a class .. etc  
=> 위와 같은 타입은 컴파일 된 클래스에서 Internal names로 나타남  
e.g)  
String == java/lang/String (. -> /)

---

### 2.1.3 Type descriptors
<div id="3"></div>

> Type descriptors of some Java types

<table width="100%">
  <tr>
    <th>Java type</th>
    <th>Type descriptor</th>
  </tr>
  <tr><td>boolean</td><td>Z</td></tr>
  <tr><td>char</td> <td>C</td></tr>
  <tr><td>byte</td> <td>B</td></tr>
  <tr><td>short</td> <td>S</td></tr>
  <tr><td>int</td> <td>I</td></tr>
  <tr><td>float</td> <td>F</td></tr>
  <tr><td>long</td> <td>J</td></tr>
  <tr><td>double</td> <td>D</td></tr>
  <tr><td>Object</td> <td>Ljava/lang/Object;</td></tr>
  <tr><td>int[]</td> <td>[I</td></tr>
  <tr><td>Object[][]</td> <td>[[L/java/lang/Object;</td></tr>  
</table>


---


<div id="4"></div>  

### 2.1.4 Method descriptors
; 파라미터 & 반환을 설명 == (parameters)return  



> Sample method descriptors

<table>
  <tr>
    <th>Method declaration in source file</th>
    <th>Method descriptor</th>
  </tr>
  <tr>
    <td>void m(int i, float f)</td>
    <td>(IF)V</td>
  </tr>
  <tr>
    <td>int m(Object o)</td>
    <td>(Ljava/lang/Object;)I</td>
  </tr>
  <tr>
    <td>int[] m(int i, String s)</td>
    <td>(ILjava/lang/String;)[I</td>
  </tr>
  <tr>
    <td>Object m(int[] i)</td>
    <td>([I)Ljava/lang/Object;</td>
  </tr>
</table>

---

## 2.2 Interfaces and components

### 2.2.1 Presentation
; ASM API를 통해 컴파일 된 클래스들을 변경 or 생성하는 것은 ClassVisitor에 기초를 두고 있음  

<div id="5"></div>

> ClassVisitor  

- 각각의 메소드는 클래스 파일 구조와 같은 이름 (위의 class file structure)
- visitAnnotation과 같이, auxiliary visitors(보조?)를 반환함
- ClassVisitor는 아래와 같은 순서대로 호출 되어야 함  
<pre>
visit visitSource? visitOuterClass? ( visitAnnotation | visitAttribute )*  
( visitInnerClass | visitField | visitMethod )*
visitEnd

== visit이 가장먼저 호출 & visitEnd가 마지막
</pre>

> ClassVisitor & FieldVisitor

<pre>
public abstract class ClassVisitor {
  public ClassVisitor(int api);

  public ClassVisitor(int api, ClassVisitor cv);

  public void visit(int version, int access, String name,
    String signature, String superName, String[] interfaces);

  public void visitSource(String source, String debug);

  public void visitOuterClass(String owner, String name, String desc);

  AnnotationVisitor visitAnnotation(String desc, boolean visible);

  public void visitAttribute(Attribute attr);

  public void visitInnerClass(String name, String outerName,
    String innerName, int access);

  public FieldVisitor visitField(int access, String name, String desc,
    String signature, Object value);

  public MethodVisitor visitMethod(int access, String name, String desc,
    String signature, String[] exceptions);

  void visitEnd();
}

public abstract class FieldVisitor {
  public FieldVisitor(int api);

  public FieldVisitor(int api, FieldVisitor fv);

  public AnnotationVisitor visitAnnotation(String desc, boolean visible);

  public void visitAttribute(Attribute attr);

  public void visitEnd();
}
</pre>


> ASM이 제공하는 핵심 컴포넌트

- **ClassReader** : 주어진 클래스파일(byte array)를 파싱 & ClassVisitor의  
visitXxx를 호출( ClassReader::acept()의 ClassVisitor 파라미터로 )
- **ClassWriter** : 컴파일된 클래스들을 binary form으로 빌드(subclass of ClassVisitor)  
ClassWriter::toByteArray()를 통해, byte array output 생성
- **ClassVisitor** : 다른 ClassVisitor로 모든 메소드 호출을 위임

---

### 2.2.2 Parsing classes
<div id="6"></div>  


**SampleCode**

> ClassPrinter

<pre>
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM5);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug){

    }

    public void visitOuterClass(String owner, String name, String desc){

    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible){
        return null;
    }

    public void visitAttribute(Attribute attr){

    }

    public void visitInnerClass(String name, String outerName, String innerName, int access){

    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd(){
        System.out.println("}");
    }
}
</pre>

> ClassPrinterRunner

<pre>
package com.asm_sample.document.ch2;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class ClassPrinterRunner {
    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        /* java/lang/Runnable을 파싱하는 ClassReader 생성 */
        ClassReader cr = new ClassReader("java.lang.Runnable");

        /* ClassLoader를 이용한 ClassReader 생성 */
        // ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // ClassLoader cl = ClassPrinterRunner.class.getClassLoader();

        /* ClassPrinter 적용 */
        cr.accept(cp, 0);
    }
}
</pre>

> output

<pre>
java/lang/Runnable extends java/lang/Object {
 run()V
}
</pre>

---

### 2.2.3 Generating Classes
;클래스를 생성하는데 유일한 컴포넌트 == ClassWriter  
<div id="7"></div>

> 생성 할 인터페이스

<pre>
package pkg;
public interface Comparable extends Mesurable {
  int LESS = -1;
  int EQUAL = 0;
  int GREATER = 1;
  int compareTo(Object o);
}
</pre>

> javap

<pre>
public interface pkg.Comparable extends pkg.Mesurable {
  public static final int LESS;

  public static final int EQUAL;

  public static final int GREATER;

  public abstract int compareTo(java.lang.Object);
}
</pre>

> Generate

<pre>
ClassWriter cw = new ClassWriter(0);

// cw.visit(version, access, name, signature, superName, interfaces);
cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});

// cw.visitField(access, name, desc, signature, value)
cw.visitField(ACC_PUBLIC + ACC_FINAL, "LESS", "I", null, new Integer(-1)).visitEnd();
cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();

// cw.visitMethod(access, name, desc, signature, exceptions)
cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
cw.visitEnd();
byte[] b = cw.toByteArray();
</pre>

---

















<br /><br /><br /><br /><br />
p
