package ch12.ex12_01;

/**
 * LinkedListは抽象クラスであるべき。
 * 理由：
 * 1.next()やhasNext()など、定型のメソッド定義を繰り返す必要がある。
 * 2.インタフェースだと、cloneをサポートするために、インタフェースにcloneを定義するか、
 *   キャストをたくさん行うかしなければいけない。
 * 3.インタフェースではコンストラクタの形式を指定できない。
 */

public interface LinkedList<E extends Object> extends Cloneable{
	public void setNext(LinkedList<E> listElement);
	public LinkedList<E> next();
	public boolean hasNext();
	public E get();
	public LinkedList<E> find(E ele) throws ObjectNotFoundException;
	public void set(E element);
	public int size();
	public void addLast(E element);
	//少し気持ち悪い
	public LinkedList<E> clone();
}
