package ch22.ex22_04;

public class Attr<V> {
	private final String name;
	private V value = null;

	public Attr(String name){
		this.name = name;
	}

	public Attr(String name, V value){
		this(name);
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public V getValue(){
		return value;
	}

	public V setValue(V newValue){
		V oldValue = value;
		this.value = newValue;
		return oldValue;
	}

	public String toString(){
		return name + "='" + value + "'";
	}
}
