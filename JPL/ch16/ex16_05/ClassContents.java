package ch16.ex16_05;

import java.lang.reflect.*;
import java.lang.annotation.*;

class ClassContents {
  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName(args[0]);
      System.out.println(c);
      printMembers(c.getFields());
      printMembers(c.getConstructors());
      printMembers(c.getMethods());
    } catch (ClassNotFoundException e) {
      System.out.println("unknown class: " + args[0]);
    }
  }

  private static void printMembers(Member[] mems) {
    for (Member m : mems) {
      if (m.getDeclaringClass() == Object.class)
        continue;
      String decl = m.toString();
      System.out.println(decl);
      printAnnotation((AnnotatedElement)m);
    }
  }

  private static void printAnnotation(AnnotatedElement mem) {
    Annotation[] annotations = mem.getAnnotations();
	if (annotations.length == 0) {
		// アノテーションがない場合
		return ;
	}
    for (Annotation a : annotations) {
      String decl = a.toString();
      System.out.println("  " + decl.replaceAll("java.lang.", ""));
    }
  }
}
