package ch11.ex11_02;

public class Attr<E> {
	private final String name;
	private Object value = null;
	
	// コンストラクタ
	public Attr(String name) {
		this.name = name;	
	}

	// コンストラクタ
	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String toString() {
		return name + ": " + value + ".";
	}
	
	
}
