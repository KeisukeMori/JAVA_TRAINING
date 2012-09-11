package ch16.ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassContents {
		
	public static void main(String[] args){
		String className = "java.io.File";
		try {
			Class<?> c = Class.forName(className);
			printClass(c);
		}
		catch (ClassNotFoundException e){
		System.out.println("unknown class : " + className);
		e.printStackTrace();
		}
	}
	
	private static void printClass(Class<?> clazz){
		printAnnotations(clazz.getFields());
		printAnnotations(clazz.getConstructors());
		printAnnotations(clazz.getMethods());
		
		for(Annotation a : clazz.getAnnotations()){
			System.out.println(a);
		}
	}
	
	private static void printAnnotations(Member[] members){
		for(Member member : members){
			Annotation[] annotations = new Annotation[0];
			if(member.getDeclaringClass() == Object.class){
				continue;
			}
			if(member instanceof Field){
				annotations = ((Field)member).getAnnotations();
			}
			if(member instanceof Constructor){
				annotations = ((Constructor)member).getAnnotations();				
			}
			if(member instanceof Method){
				annotations = ((Method)member).getAnnotations();
			}
			for(Annotation a : annotations){
				System.out.println(a);
			}
			String decl = member.toString();
		}
	}
	private static String strip(String origin, String stripStr){
		return origin.replace(stripStr, "");
	}
}