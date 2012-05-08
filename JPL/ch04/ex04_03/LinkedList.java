package ch04.ex04_03;


// LinkedListは他のクラスで拡張される可能性があるためインターフェースで定義されるべき。
public interface LinkedList<E> {
	void show();
	String toString();
	Object getObj();

	void setObj(Object Obj);
	E getNextNode();

	void setNextNode(E nextNode);
	int countList();
}
