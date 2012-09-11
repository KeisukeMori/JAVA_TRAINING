package ch16.ex16_09;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class ClassContents {
	private static String[]
			basic	= {"class", "interface", "enum", "annotation"};
	
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
		printType(clazz, basic, false);
		System.out.println("{");
		System.out.println("//field");
		printFields(clazz.getDeclaredFields());
		System.out.println("//constructor");
		printConstructors(clazz.getDeclaredConstructors());
		System.out.println("//methods");
		printMethod(clazz.getDeclaredMethods());
		System.out.println("}");
	}
	
	private static void printConstructors(Constructor<?>[] constructors){
		for(Constructor<?> c : constructors){
			if(c.getDeclaringClass() == Object.class){
				continue;
			}
			System.out.print(" ");
			printAnnotations(c);
			int modifier = c.getModifiers();
			String modifiers = Modifier.toString(modifier);
			System.out.print(modifiers + " " + c.getDeclaringClass().getSimpleName() + "(");
			
			//print parameters
			Class<?>[] params = c.getParameterTypes();
			if(params.length > 0){
				StringBuilder str = new StringBuilder();
				for(Class<?> param : params){
					printAnnotations(param);
					str.append(param.getSimpleName() + createGenericString(param) + ", ");
				}
				str.delete(str.length() -2, str.length());
				System.out.print(str);
			}
			System.out.print(")");
			//print Throwable
			Class<?>[] exceptions = c.getExceptionTypes();
			if(exceptions.length > 0){
				System.out.print("throws ");
				StringBuilder str = new StringBuilder();
				for(Class<?> ex : exceptions){
					printAnnotations(ex);
					str.append(ex.getSimpleName() + createGenericString(ex) + ", ");
				}
				str.delete(str.length() -2, str.length());
				System.out.print(str);
			}
			System.out.println(";");

		}
	}
	private static void printMethod(Method[] methods){
		for(Method m : methods){
			if(m.getDeclaringClass() == Object.class){
				continue;
			}
			System.out.print(" ");
			printAnnotations(m);
			int modifier = m.getModifiers();
			String modifiers = Modifier.toString(modifier);
			String returnType = m.getReturnType().getSimpleName();
			System.out.print(modifiers + " " + returnType + " " + m.getName() + "(");
			
			//print parameters
			Class<?>[] params = m.getParameterTypes();
			if(params.length > 0){
				StringBuilder str = new StringBuilder();
				for(Class<?> param : params){
					printAnnotations(param);
					str.append(param.getSimpleName() + createGenericString(param) + ", ");
				}
				str.delete(str.length() -2, str.length());
				System.out.print(str);
			}
			System.out.print(")");
			//print Throwable
			Class<?>[] exceptions = m.getExceptionTypes();
			if(exceptions.length > 0){
				System.out.print("throws ");
				StringBuilder str = new StringBuilder();
				for(Class<?> ex : exceptions){
					printAnnotations(ex);
					str.append(ex.getSimpleName() + createGenericString(ex) + ", ");
				}
				str.delete(str.length() -2, str.length());
				System.out.print(str);
			}
			System.out.println(";");

		}
	}
	private static void printFields(Field[] fields){
		for(Field f : fields){
			if(f.getDeclaringClass() == Object.class){
				continue;
			}
			printAnnotations(f);
			System.out.print(" ");
			int modifier = f.getModifiers();
			String modifiers = Modifier.toString(modifier);
			System.out.println(modifiers + " " + f.getType().getSimpleName() + " " + f.getName() + ";");
		}
	}

	
	private static void printType(Type type, String[] labels, boolean genericOnly){
		if(type == null){
			return;
		}
		//converting Type to Class 
		Class<?> classz = null;
		if(type instanceof Class<?>){
			classz = (Class<?>) type;
		}
		else if(type instanceof ParameterizedType){
			classz = (Class<?>)((ParameterizedType)type).getRawType();
		}
		else{
			throw new Error("Unexpected non-class type");
		}
		if(classz == java.lang.Object.class){
			return;
		}
		if(!genericOnly) {
			//print this Type object
			int kind = classz.isAnnotation() ? 3 : 
				classz.isEnum() ? 2 :
				classz.isInterface() ? 1 : 0;
			int modifier = classz.getModifiers();
			String modifiers = Modifier.toString(modifier);
			System.out.print(modifiers + " " + labels[kind] + " ");
		}
		System.out.print(classz.getSimpleName());
		
		//printGenericType
		System.out.print(createGenericString(classz));

		Class<?> superCls = classz.getSuperclass();
		if(superCls != null && superCls != java.lang.Object.class){
			System.out.print(" extends ");
			System.out.println(superCls.getSimpleName());
			System.out.println(createGenericString(superCls));
		}
		//print all interfaces that this class implements
		Class<?>[] interfaces = classz.getInterfaces();
		if(interfaces.length > 0){
			System.out.print(" implements ");
			StringBuilder str = new StringBuilder();
			for(Class<?> interfacez : interfaces){
				str.append(interfacez.getSimpleName() + createGenericString(interfacez) + ", ");
			}
			str.delete(str.length() -2, str.length());
			System.out.println(str);
		}
	}
	private static String strip(String origin, String stripStr){
		return origin.replace(stripStr, "");
	}
	
	public static String createGenericString(Class<?> classz){
		StringBuilder builder = new StringBuilder();
		//print generic type parameters, if it exists.
		TypeVariable<?> [] params = classz.getTypeParameters();
		if(params.length > 0){
			builder.append('<');
			for(TypeVariable<?> param : params){
				builder.append(param.getName() );
				builder.append(", ");
			}
			builder.delete(builder.length() -2, builder.length());
			builder.append(">");
		}
		return builder.toString();
	}
	
	public static void printAnnotations(AnnotatedElement elem){
		Annotation[] annotations = elem.getDeclaredAnnotations();
		for(Annotation a : annotations){
			System.out.println("@"+a.annotationType().getSimpleName());
		}
	}
}
