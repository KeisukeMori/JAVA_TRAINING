package interpret;



import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Oparator {
	private MainWindow mainWindow;
	private ConstructorModel constModel;
	private ObjectModel objectModel;

	public Oparator(MainWindow mainFrame) {
		this.mainWindow = mainFrame;
		constModel = new ConstructorModel();
		objectModel = new ObjectModel();
	}

	public void searchButton(String text) {
		if (isEmptyString(text)) return;

		Class<?> cls = null;
		try {
			cls = Class.forName(text);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (cls != null) {
			System.out.println(cls.getCanonicalName());
			System.out.println(cls.getName());
			Constructor<?>[] cons = cls.getConstructors();
			Method[] methods = cls.getMethods();
			constModel.saveConstructors(cons);
			mainWindow.printConstructorList(cons);
			mainWindow.printMethodList(methods);
		} else {
			System.err.println("No such type: \"" + text + "\"");
		}
	}

	public void selectButton(String constName) {
		Constructor<?> con = constModel.getConstructor(constName);
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

		Constructor<?> con = constModel.getConstructor(constName);

		if (!createObject(objName, con, params)) {
			return false; // オブジェクトの生成失敗
		}

		return true;
	}

	public void objectClearButton() {
		objectModel.clearObjects();
	}

	public void constClearButton() {
		constModel.clearConstructors();
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
		Type[] types = con.getGenericParameterTypes();
		System.out.println("types" + types.length );  
		/* デフォルトコンストラクタの場合 */
		if (types.length == 0) {
			try {
				System.out.println("objName" + objName );  
				Object obj = con.newInstance();
				objectModel.saveObject(objName, obj);
				return true;
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
		}

		String[] params = csvParse(paramStr);
		if (types.length != params.length) {
			System.out.println("引数の数が一致しません");
			return false;
		}
		System.out.println("objName" + objName );  
//		Object obj;
//		try {
//			obj = con.newInstance();
//			objectModel.saveObject(objName, obj);
//		} catch (IllegalArgumentException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
		return true;
	}

	private String[] csvParse(String csv) {
		return csv.split(",", 0);
	}

	public static List<Method> getMethods(Object obj) {
		List list = new ArrayList<Method>();

//		Method[] method1 = obj.getClass().getDeclaredMethods();
		Method[] method2 = obj.getClass().getMethods();

		for(Method m: method2) {
			list.add(m);
		}

//		for(Method m: method1) {
//			if(!m.toString().startsWith("public")){
//				list.add(m);
//			}
//		}
		return list;
	}
	
	public static Object runMethod(Object o, Method m, Object... args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return m.invoke(o, args);
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
	
}


