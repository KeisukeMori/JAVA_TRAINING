package interpret;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

		Class<?> clazz = null;
		try {
			clazz = Class.forName(text);
		} catch (ClassNotFoundException e) {
			causeException(e);
		}

		if (clazz == null) {
			System.err.println("No such type: \"" + text + "\"");
		}

		System.out.println(clazz.getCanonicalName());
		System.out.println(clazz.getName());
		Constructor<?>[] cons = clazz.getConstructors();

		for (Constructor<?> con : cons) {
			String constName = con.toString();
			constName = getSimpleName(constName);
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
			System.out.println("オブジェクト名が空です");
			return false;
		} else if (objectType.isEntried(objName)) {
			System.out.println("同じ名称のオブジェクトがあります");
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
		String[] params = csvSplit(paramStr);
		Object[] paramObj = createParameter(con.getGenericParameterTypes(), params);
		System.out.println("params" + params.length );  
		System.out.println("objName" + objName );

		if (paramObj == null) {
			System.out.println("パラメータ不正です");
			return false;
		}

		try {
			Object obj = con.newInstance(paramObj);
			objectType.saveObject(objName, obj);
		} catch (IllegalAccessException e) {
			causeException(e);
			return false;
		} catch (InvocationTargetException e) {
			causeException(e);
			return false;
		} catch (InstantiationException e) {
			causeException(e);
			return false;
		}

		return true;
	}

	private String[] csvSplit(String csv) {
		System.out.println("csvSplit");
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

			Class<?> clazz = (Class<?>)type;
			String typeName = clazz.getSimpleName();
			Object obj = objectType.getObject(param);

			if (objectType.isEntried(param)
					&& clazz.isInstance(obj)) {
				objects[i] = obj;
			} else if (getObject(param) != null) {
				objects[i] = getObject(param);
			} else if (typeName.equals("byte") || typeName.equals("Byte")) {
				objects[i] = Byte.valueOf(param);
			} else if (typeName.equals("short") || typeName.equals("Short")) {
				objects[i] = Short.valueOf(param);
			} else if (typeName.equals("int") || typeName.equals("Integer")) {
				objects[i] = Integer.valueOf(param);
			} else if (typeName.equals("long") || typeName.equals("Long")) {
				objects[i] = Long.valueOf(param);
			} else if (typeName.equals("char") || typeName.equals("Character")) {
				if (param.length() != 1) {
					return null;
				} else {
					objects[i] = Character.valueOf(param.charAt(0));
				}
			} else if (typeName.equals("float") || typeName.equals("Float")) {
				objects[i] = Float.valueOf(param);
			} else if (typeName.equals("double") || typeName.equals("Double")) {
				objects[i] = Double.valueOf(param);
			} else if (typeName.equals("boolean") || typeName.equals("Boolean")) {
				objects[i] = Boolean.valueOf(param);
			} else if (typeName.equals("String")) {
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
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		String fieldName = str.substring(endIndex + 1);
		Field field = null;
		try {
			field = clazz.getField(fieldName);
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
			System.out.println("オブジェクトが取得できません");
			return;
		}

		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getMethods();

		ObjectValuePair[] methodPairs = new ObjectValuePair[methods.length];
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			String simpleMethodName = getSimpleName(method.toString());
			methodPairs[i] = new ObjectValuePair(simpleMethodName, method);
		}

		Arrays.sort(methodPairs);

		for (ObjectValuePair mPair : methodPairs) {
			mainWindow.printMethod(mPair.key);
			methodType.saveMethod(mPair.key, (Method)mPair.obj);
		}
	}

	public boolean methodRunButton() {
		String objName = mainWindow.getSelectedObject();
		String methodName = mainWindow.getMethodName();
		System.out.println("objName: " + objName);
		System.out.println("methodName: " + methodName);
		Object obj = objectType.getObject(objName);
		Method method = methodType.getMethod(methodName);
		String params = mainWindow.getRunMethodParams();

		if (method == null) {
			System.out.println("メソッド" + methodName + " は存在しません");
			return false;
		}

		method.setAccessible(true);
		if (!methodInvoke(obj, method, params)) {
			System.out.println("メソッドの実行に失敗しました");
			return false;
		}

		return true;
	}

	private boolean methodInvoke(Object onThis, Method method, String paramStr) {
		System.out.println("paramStr" + paramStr);
		String[] params = csvSplit(paramStr);
		Object[] paramObj = createParameter(method.getGenericParameterTypes(), params);

		if (paramObj == null) {
			System.out.println("パラメータ不正です");
			return false;
		}

		try {
			Object output = method.invoke(onThis, paramObj);
			if (output != null)
				System.out.println("戻り値　" + output.toString());
		} catch (IllegalAccessException e) {
			causeException(e);
			return false;
		} catch (InvocationTargetException e) {
			causeException(e);
			return false;
		}

		return true;
	}

	public void fieldBtn() {
		String objName = mainWindow.getSelectedObject();
		Object obj = objectType.getObject(objName);

		if (obj == null) {
			System.out.println("オブジェクトが取得できません");
			return;
		}

		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		ObjectValuePair[] fieldPairs = new ObjectValuePair[fields.length];
		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];
			String fieldName = field.toString();
			fieldName = getSimpleName(fieldName);
			fieldPairs[i] = new ObjectValuePair(fieldName, field);
		}

		Arrays.sort(fieldPairs);

		for (ObjectValuePair fPair : fieldPairs) {
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
			System.out.println("フィールドが取得できません");
			return false;
		} else {
			System.out.println(fieldName);
		}

		String paramStr = mainWindow.getFieldValue();
		String[] params = csvSplit(paramStr);

		Object[] paramObj = createParameter(new Type[]{field.getGenericType()}, params);

		if (paramObj == null) {
			System.out.println("パラメータ不正です");
			return false;
		}

		field.setAccessible(true);
		try {
			field.set(obj, paramObj[0]);
			System.out.println("フィールドを設定しました");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	  public void fieldValueGetButton() {
			String objName = mainWindow.getSelectedObject();
			Object obj = objectType.getObject(objName);
			String fieldName = mainWindow.getFieldName();
			Field field = fieldType.getField(fieldName);

		    if (field == null) {
		      System.out.println("フィールドが取得できません");
		      return;
		    }

		    field.setAccessible(true);
		    try {
		      Object tmp = field.get(obj);
		      if (tmp == null)
		        mainWindow.setFieldLabel("value is : null");
		      else
		    	  mainWindow.setFieldLabel("value is : " + tmp.toString());
		    } catch (IllegalAccessException e) {
		      causeException(e);
		      return;
		    }
		  }
	
	public boolean setArrayButton() {
		String objName = mainWindow.getSelectedObject();
		String clsName = mainWindow.getClassName();
		String param = mainWindow.getParam();
		int arrayIndex = mainWindow.getSelectedRowIndex();
		Object obj = objectType.getObject(objName);

		if (isEmptyString(param)) {
			System.out.println("パラメータを入力してください");
			return false;
		} else if (obj == null) {
			System.out.println("オブジェクト" + objName + "が取得できません ");
			return false;
		}

		Class<?> clazz = null;
		try {
			clazz = Class.forName(clsName);
		} catch (ClassNotFoundException e) {
			System.out.println("クラス" + clsName+ "が見つかりません ");
			causeException(e);
			return false;
		}

		Object[] paramObj = createParameter(new Type[]{clazz}, new String[]{param});
		if (paramObj == null) {
			System.out.println("パラメータ不正です");
			return false;
		}

		Array.set(obj, arrayIndex, paramObj[0]);
		String objAryName = objName + "[" + arrayIndex + "]";
		System.out.println(objAryName + " を " + paramObj[0] + " にしました");

		objectType.saveObject(objAryName, paramObj[0]);
		mainWindow.addObjectName(objAryName);
		mainWindow.setObjectTable(objName, (Object[])obj);

		return true;
	}

	public List<Method> getMethods(Object obj) {
		List list = new ArrayList<Method>();

		//		Method[] method1 = obj.getClass().getDeclaredMethods();
		Method[] methods = obj.getClass().getMethods();

		for(Method m: methods) {
			list.add(m);
		}
		setMethod(methods);
		return list;
	}

	public static void setMethod(Method[] methods) {
		method = methods;
	}

	public Method[] getMethod() {
		return method;
	}

	public boolean createAryButton() {
		String clsName = mainWindow.getClassName();
		String objName = mainWindow.getAryObjName();
		String aryNumStr = mainWindow.getAryNumStr();

		if (isEmptyString(clsName)) {
			System.out.println("クラス名を入力してください");
			return false;
		} else if (isEmptyString(objName)) {
			System.out.println("配列オブジェクト名を入力してください");
			return false;
		} else if (objectType.isEntried(objName)) {
			System.out.println("同じ名称のオブジェクトがあります");
			return false;
		}

		int aryNum = 0;
		try {
			aryNum = Integer.parseInt(aryNumStr);
		} catch (NumberFormatException e) {
			causeException(e);
			System.out.println("整数を入力してください ");
			return false;
		}
		Class<?> clazz = null;
		try {
			clazz = Class.forName(clsName);
		} catch (ClassNotFoundException e) {
			causeException(e);
			System.out.println("クラス " + clsName + " が見つかりません");
			return false;
		}

		Object array = Array.newInstance(clazz, aryNum);
		objectType.saveObject(objName, array);

		return true;
	}
	public void arrayButton() {
		String objName = mainWindow.getSelectedObject();
		Object obj = objectType.getObject(objName);

		if (obj == null) {
			System.out.println("オブジェクトが取得できません");
			return;
		} else if (!obj.getClass().isArray()) {
			System.out.println("配列オブジェクトを選択してください");
			return;
		}

		mainWindow.setArrayTable(objName, (Object[])obj);
		mainWindow.setVisible(true);
	}


	private String getSimpleName(String name) {
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
					String params = str.substring(parenIndex + 1, eParenIndex);
					String[] paramAry = params.split(",");
					for (int j = 0; j < paramAry.length; ++j) {
						paramAry[j] = getSimpleName(paramAry[j]);
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


	class ObjectValuePair implements Comparable<ObjectValuePair> {
		String key;
		Object obj;

		ObjectValuePair(String key, Object obj) {
			this.key = key;
			this.obj = obj;
		}

		public int compareTo(ObjectValuePair other) {
			return key.compareTo(other.key);
		}
	}

	private static void causeException(Exception e) {
		if (e.getCause() != null) {
			e.getCause().printStackTrace();
		} else {
			e.printStackTrace();
		}
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
				causeException(e);

			} catch (IllegalAccessException e) {
				causeException(e);

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

	public class ObjectType {
		HashMap<String, Object> objects;

		public ObjectType() {
			objects = new HashMap<String, Object>();
		}
		public void saveObject(String key, Object obj) {
			System.out.println("saveObject key: " + key );  
			objects.put(key, obj);
		}

		public Object getObject(String key) {
			System.out.println("getObject key: " + key );  
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
		HashMap<String, Constructor<?> > constructors;

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

		HashMap<String, Method> methods;

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

		HashMap<String, Field> fields;

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


