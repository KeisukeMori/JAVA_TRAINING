package ch12.ex12_01;

/**
 * 良い考えかどうか：genericにする必要はない。Attrが扱う要素は本来Attrで隠蔽されるべき。
 * インターフェースへの影響としてはインターフェースもジェネリクスで定義しなければならない。
 * AttributedオブジェクトもAttrの中身は知る必要はないため意味はない。
 **/
 
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
		return name + "='" + value + "'";
	}
	
}
