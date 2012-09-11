package interpret;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Oparator {
	private MainWindow mainWindow;
	private ConstructorType constType;
	private ObjectType objectType;
	public static Method[] method;
	private MethodType methodType;
	private FieldType fieldType;

	public Oparator(MainWindow mainFrame) {
		this.mainWindow = mainFrame;
		constType = new ConstructorType();
		objectType = new ObjectType();
		methodType = new MethodType();
		fieldType = new FieldType();
	}

	public void searchButton(String text) {
		if (isEmptyString(text)) return;

		Class<?> cls = null;
		try {
			cls = Class.forName(text);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (cls == null) {
			System.err.println("No such type: \"" + text + "\"");
		}

		System.out.println(cls.getCanonicalName());
		System.out.println(cls.getName());
		Constructor<?>[] cons = cls.getConstructors();

		for (Constructor<?> con : cons) {
			String constName = con.toString();
			constName = simplifyName(constName);
			constType.saveConstructor(constName, con);
			mainWindow.printConstructor(constName);
		}

	}

	public void selectButton(String constName) {
		Constructor<?> con = constType.getConstructor(constName);
		if (con == null) return;

	}

	public boolean createButton() {
		String objName = mainWindow.getObjectName();
		String constName = mainWindow.getConstName();
		String params = mainWindow.getParamName();
		if (isEmptyString(objName)) {
			System.out.println("オブジェクト名なし");
			return false;
		}
		Constructor<?> con = constType.getConstructor(constName);

		if (!createObject(objName, con, params)) {
			return false; // オブジェクトの生成失敗
		}
		return true;
	}

	public void objectClearButton() {
		objectType.clearObjects();
	}

	public void constClearButton() {
		constType.clearConstructors();
	}

	private boolean isEmptyString(String str) {
		if (str.length() == 0) return true;
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == ' ')
				return true;
		}
		return false;
	}

	private boolean createObject(String objName, Constructor<?> con, String paramStr) {
		String[] params = csvParse(paramStr);
		Object[] pObjs = createParameter(con.getGenericParameterTypes(), params);
		System.out.println("params" + params.length );  
		System.out.println("objName" + objName );

		if (pObjs == null) {
			System.out.println("パラメータが不正です");
			return false;
		}

		try {
			Object obj = con.newInstance(pObjs);
			objectType.saveObject(objName, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private String[] csvParse(String csv) {
		return csv.split(",", 0);
	}

	private Object[] createParameter(Type[] types, String[] params) {
		if (types.length == 0)
			return new Object[0];
		else if (types.length != params.length)
			return null;

		Object[] objects = new Object[types.length];

		for (int i = 0; i < objects.length; ++i) {
			Type type = types[i];
			String param = params[i];

			Class<?> cls = (Class<?>)type;
			String typeName = cls.getName();

			if (objectType.isEntried(param)) { // オブジェクトが存在する場合はそれを利用する
				objects[i] = objectType.getObject(param);
			} else if (getObject(param) != null) {
				objects[i] = getObject(param);
			} else if (typeName.equals("byte")) {
				objects[i] = Byte.valueOf(param);
			} else if (typeName.equals("short")) {
				objects[i] = Short.valueOf(param);
			} else if (typeName.equals("int")) {
				objects[i] = Integer.valueOf(param);
			} else if (typeName.equals("long")) {
				objects[i] = Long.valueOf(param);
			} else if (typeName.equals("char")) {
				if (param.length() != 1) {
					return null;
				} else {
					objects[i] = Character.valueOf(param.charAt(0));
				}
			} else if (typeName.equals("float")) {
				objects[i] = Float.valueOf(param);
			} else if (typeName.equals("double")) {
				objects[i] = Double.valueOf(param);
			} else if (typeName.equals("boolean")) {
				objects[i] = Boolean.valueOf(param);
			} else if (typeName.equals("java.lang.String")) {
				objects[i] = param;
			} else {
				return null;
			}
		}

		return objects;
	}

	private Object getObject(String str) {
		int endIndex = str.lastIndexOf('.');
		if (endIndex == -1)  return null;

		String className = str.substring(0, endIndex);
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		String fieldName = str.substring(endIndex + 1);
		Field field = null;
		try {
			field = cls.getField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		}

		Object obj = null;
		try {
			obj = field.get(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

		return obj;
	}

	public void methodBtn() {
		String objName = mainWindow.getSelectedObject();
		Object obj = objectType.getObject(objName);

		if (obj == null) {
			System.out.println("オブジェクトが見つかりません");
			return;
		}

		Class<?> cls = obj.getClass();
		Method[] methods = cls.getMethods();

		ObjectPair[] methodPairs = new ObjectPair[methods.length];
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			String simpleMethodName = simplifyName(method.toString());
			methodPairs[i] = new ObjectPair(simpleMethodName, method);
		}

		Arrays.sort(methodPairs);

		for (ObjectPair mPair : methodPairs) {
			mainWindow.printMethod(mPair.key);
			methodType.saveMethod(mPair.key, (Method)mPair.obj);
		}
	}

	public boolean methodRunButton() {
		String objName = mainWindow.getObjectName();
		String methodName = mainWindow.getMethodName();
		Object obj = objectType.getObject(objName);
		Method method = methodType.getMethod(methodName);
		String params = mainWindow.getRunMethodParams();

		if (method == null) {
			System.out.println("メソッド: " + methodName + " は存在しません");
			return false;
		}

		method.setAccessible(true);
		if (!methodInvoke(obj, method, params)) {
			System.out.println("メソッド呼び出しに失敗しました");
			return false;
		}

		return true;
	}

	private boolean methodInvoke(Object onThis, Method method, String paramStr) {
		String[] params = csvParse(paramStr);
		Object[] pObjs = createParameter(method.getGenericParameterTypes(), params);

		if (pObjs == null) {
			System.out.println("パラメータが不正です");
			return false;
		}

		try {
			Object output = method.invoke(onThis, pObjs);
			if (output != null)
				System.out.println(output.toString()); // 可能であれば文字列に変換して出力する
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public void fieldBtn() {
		String objName = mainWindow.getSelectedObject();
		Object obj = objectType.getObject(objName);

		if (obj == null) {
			System.out.println("オブジェクトが見つかりません");
			return;
		}

		Class<?> cls = obj.getClass();
	    Field[] fields = cls.getDeclaredFields();

	    ObjectPair[] fieldPairs = new ObjectPair[fields.length];
	    for (int i = 0; i < fields.length; ++i) {
	      Field field = fields[i];
	      String fieldName = field.toString();
	      fieldName = simplifyName(fieldName);
	      fieldPairs[i] = new ObjectPair(fieldName, field);
	    }
	    
	    Arrays.sort(fieldPairs);
	    
	    for (ObjectPair fPair : fieldPairs) {
	        mainWindow.printField(fPair.key);
	        fieldType.saveField(fPair.key, (Field)fPair.obj);
	      }
	}
	
	  public boolean fieldSetButton() {
		    String objName = mainWindow.getSelectedObject();
		    Object obj = objectType.getObject(objName);
		    String fieldName = mainWindow.getFieldName();
		    Field field = fieldType.getField(fieldName);

		    if (field == null) {
		      System.out.println("フィールドが見つかりません");
		      return false;
		    }

		    String paramStr = mainWindow.getFieldValue();
		    String[] params = csvParse(paramStr);

		    Object[] objs = createParameter(new Type[]{field.getGenericType()}, params);

		    if (objs == null) {
		      System.out.println("パラメータが不正です");
		      return false;
		    }

		    field.setAccessible(true);
		    try {
		      field.set(obj, objs[0]);
		      System.out.println("フィールドを設定しました");
		    } catch (IllegalAccessException e) {
		      e.printStackTrace();
		      return false;
		    }

		    return true;
		  }
	
	public List<Method> getMethods(Object obj) {
		List list = new ArrayList<Method>();

		//		Method[] method1 = obj.getClass().getDeclaredMethods();
		Method[] methods = obj.getClass().getMethods();

		for(Method m: methods) {
			list.add(m);
		}

		//		for(Method m: method1) {
		//			if(!m.toString().startsWith("public")){
		//				list.add(m);
		//			}
		//		}
		setMethod(methods);
		return list;
	}

	public static void setMethod(Method[] methods) {
		method = methods;
	}

	public Method[] getMethod() {
		return method;
	}


	public static Object runMethod(Object obj, Method method, Object... args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return method.invoke(obj, args);
	}

	public static List getFields(Object o){
		Field[] fields = o.getClass().getDeclaredFields();
		List list = new ArrayList();

		for(Field f : fields) {
			f.setAccessible(true);
			list.add(f);
		}


		return list;
	}
	public static List getValues(Object o){
		Field[] values = o.getClass().getDeclaredFields();
		List list = new ArrayList();

		for(Field f : values) {
			try {
				f.setAccessible(true);
				list.add(f.get(o));
			} catch (IllegalArgumentException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		return list;
	}

	public static Object setField(Object obj, Field fld, String value) throws IllegalArgumentException, IllegalAccessException {
		fld.setAccessible(true);
		if(fld.getType().equals(boolean.class)) {
			fld.setBoolean(obj, Boolean.parseBoolean(value));
		} else if (fld.getType().equals(byte.class)) {
			fld.setByte(obj, Byte.parseByte(value));
		} else if (fld.getType().equals(char.class)) {
			fld.setChar(obj, value.charAt(0));
		} else if (fld.getType().equals(short.class)) {
			fld.setShort(obj, Short.parseShort(value));
		} else if (fld.getType().equals(int.class)) {
			fld.setInt(obj, Integer.parseInt(value));
		} else if (fld.getType().equals(long.class)) {
			fld.setLong(obj, Long.parseLong(value));
		} else if (fld.getType().equals(float.class)) {
			fld.setFloat(obj, Float.parseFloat(value));
		} else if (fld.getType().equals(double.class)) {
			fld.setDouble(obj, Double.parseDouble(value));
		} else {
			fld.set(obj, value);
		}

		return obj;
	}

	private String simplifyName(String name) {
		String str = "";

		String[] tmpAry = name.split(" "); // 修飾子と分ける

		for (int i = 0; i < tmpAry.length; ++i) {
			if (tmpAry[i].indexOf('.') != -1) { // . を含むものを簡約化する
				str = tmpAry[i];

				if (str.indexOf('(') != -1) { // メソッドの時
					int parenIndex = str.indexOf('(');
					int eParenIndex = str.indexOf(')');
					int beginIndex = str.lastIndexOf('.', parenIndex) + 1;
					String mName = str.substring(beginIndex, parenIndex);
					//tmpAry[i] = mName;
					/* パラメータも簡約 */
					String params = str.substring(parenIndex + 1, eParenIndex);
					String[] paramAry = params.split(",");
					for (int j = 0; j < paramAry.length; ++j) {
						paramAry[j] = simplifyName(paramAry[j]);
					}
					StringBuffer buf = new StringBuffer();
					for (String s : paramAry) {
						buf.append(s);
						buf.append(",");
					}
					params = buf.toString();
					params = params.substring(0, params.length() - 1);
					tmpAry[i] = mName + "(" + params + ")";
				} else {
					int beginIndex = str.lastIndexOf('.') + 1;
					tmpAry[i] = str.substring(beginIndex);
				}
			}
		}

		StringBuffer buf = new StringBuffer();
		for (String s : tmpAry) {
			buf.append(s);
			buf.append(" ");
		}
		String ret = buf.toString();
		ret = ret.substring(0, ret.length() - 1);

		return ret;
	}


	class ObjectPair implements Comparable<ObjectPair> {
		String key;
		Object obj;

		ObjectPair(String key, Object obj) {
			this.key = key;
			this.obj = obj;
		}

		@Override
		public int compareTo(ObjectPair other) {
			return key.compareTo(other.key);
		}
	}
}


