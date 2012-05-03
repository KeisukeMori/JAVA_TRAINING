package ch01.ex01_15;

import com.sun.corba.se.impl.naming.pcosnaming.NameServer;

public class ExtensionLookupImpl implements LookupImpl {

	public String[] names;
	public Object[] values;	

	public Object find(String name) {
		for (int i = 0; i < names.length; i++){
			if (names[i].equals(name)) {
				return values[i];
			}
		}
		return null; // 見つからなかった
	}
	
	public void add(Object value) {
		int length = values.length;
		for (int i = 0; i < length; i++) {
			values[i] = value;
		}
	}
	
	public Object[] remove(String name) {
		for (int i = 0; i < names.length; i++){
			if (names[i].equals(name)) {
				values[i] = null;
			}
		}
		return values;
	}
	public static void main(String[] args) {
		ExtensionLookupImpl test = new ExtensionLookupImpl();
		test.names = new String[]{"abc", "def"};
		test.values = new Object[]{100, 200};
		
		
		test.add(300);
		System.out.println(test.values[0]);
		
		Object[] remover = test.remove("abc");
		System.out.println(remover[0]);
	}

}