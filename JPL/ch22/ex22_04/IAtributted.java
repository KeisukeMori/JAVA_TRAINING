package ch22.ex22_04;

public interface IAtributted<V> {
	void Add(Attr<V> attr);
	Attr<V> find(String attrName);
	Attr<V> remove(String attrName);
	java.util.Iterator<Attr<V>> attrs();
}
