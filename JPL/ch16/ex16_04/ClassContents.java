package ch16.ex16_04;

import java.lang.annotation.*;

class ClassContents {
  public static void main(String[] args) {
    try {
      String className = "java.io.File";
      Class<?> c = Class.forName(className);
      System.out.println(c);
      printAnnotations(c.getAnnotations());
    } catch (ClassNotFoundException e) {
      System.out.println("unknown class: " + args[0]);
    }
  }

  private static void printAnnotations(Annotation[] annotations) {
    for (Annotation a : annotations) {
      String decl = a.toString();
      System.out.println(decl);
    }
  }
}
