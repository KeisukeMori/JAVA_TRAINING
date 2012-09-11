package interpret;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ContentsType {
	
	  private HashMap<String, Object> objects;
	  private HashMap<String, Constructor<?> > constructors;
	  private HashMap<String, Method> methods;
	  private HashMap<String, Field> fields;
	  
	public class ObjectType {

		  public ObjectType() {
		    objects = new HashMap<String, Object>();
		  }
		  public void saveObject(String key, Object obj) {
				System.out.println("saveObject key" + key );  
		    objects.put(key, obj);
		  }

		  public Object getObject(String key) {
				System.out.println("key" + key );  
		    return objects.get(key);
		  }

		  public boolean isEntried(String key) {
		    return objects.containsKey(key);
		  }

		  public void clearObjects() {
		    objects.clear();
		  }
		}
	
	public class ConstructorType {

		  public ConstructorType() {
		    constructors = new HashMap<String, Constructor<?> >();
		  }

		  public void saveConstructors(Constructor<?>[] cons) {
		    for (Constructor<?> con : cons) {
		      constructors.put(con.toString(), con);
		    }
		  }

		  public void saveConstructor(String key, Constructor<?> con) {
			    constructors.put(key, con);
			  }
		  
		  public Constructor<?> getConstructor(String key) {
		    return constructors.get(key);
		  }

		  public void clearConstructors() {
		    constructors.clear();
		  }
		}

	
	public class MethodType {

		  public MethodType() {
		    methods = new HashMap<String, Method>();
		  }

		  public void saveMethod(String methodName, Method method) {
		    methods.put(methodName, method);
		  }

		  public Method getMethod(String methodName) {
		    return methods.get(methodName);
		  }

		  public void clearMethods() {
		    methods.clear();
		  }
	}
	
	public class FieldType {

		  public FieldType() {
		    fields = new HashMap<String, Field>();
		  }

		  public void saveField(String fieldName, Field field) {
		    fields.put(fieldName, field);
		  }

		  public Field getField(String fieldName) {
		    return fields.get(fieldName);
		  }

		  public void clearFields() {
		    fields.clear();
		  }
		}
}
