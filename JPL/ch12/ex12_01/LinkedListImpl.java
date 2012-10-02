package ch12.ex12_01;

public class LinkedListImpl<E> implements LinkedList<E>{
	//2-14 変更を許すアクセッサを持つべき
	private E element;
	//2-14 変更を許すアクセッサを持つべき
	private LinkedList<E> next;

	public static void main(String [] args){
		StringBuffer sb1 = new StringBuffer("1");
		StringBuffer sb2 = new StringBuffer("2");
		StringBuffer sb3 = new StringBuffer("3");

		LinkedListImpl<StringBuffer> l = new LinkedListImpl<StringBuffer>(sb1, sb2, sb3);
		LinkedListImpl<StringBuffer> l2 = l.clone();
		l2.setNext(l);
		( (StringBuffer)l.get()).append("(list1 Append)");

		System.out.println(l);
		System.out.println(l2);
	}

	//要素だけのコンストラクタ
	public LinkedListImpl(E element){
		this.element = element;
	}

	//次のリスト要素を与えることの出来るコンストラクタ
	public LinkedListImpl(E element, LinkedList<E> next){
		this(element);
		this.next = next;
	}

	//2-12
	//可変長引数でリストを作成することの出来るコンストラクタ
	public LinkedListImpl(E... elementsArray){
		if(elementsArray == null || elementsArray.length < 1){
			throw new IllegalArgumentException();
		}
		LinkedListImpl<E> nextElement = null;
		for(int i = elementsArray.length -1; i > 0; i--){
			LinkedListImpl<E> element = new LinkedListImpl<E>(elementsArray[i], nextElement);
			nextElement = element;
		}
		this.element = elementsArray[0];
		this.setNext(nextElement);
	}

	public void setNext(LinkedList<E> listElement){
		this.next = listElement;
	}
	//2-14
	public LinkedList<E> next(){
		return next;
	}
	public boolean hasNext(){
		return next != null;
	}
	//2-14
	public E get(){
		return element;
	}
	//2-14
	public void set(E element){
		this.element = element;
	}
	//2-16
	public int size(){
		LinkedList<E> current = this;
		int size = 1;

		while(current.hasNext()){
			current = current.next();
			size++;
		}
		return size;
	}

	public void addLast(E element){
		LinkedList<E> l = this;
		while(l.hasNext()){
			l = l.next();
		}
		l.setNext(new LinkedListImpl<E>(element) );
	}

	//2-11
	@Override
	public String toString(){
		StringBuffer retString = new StringBuffer();
		LinkedList<E> current = this;

		retString.append(this.element);
		while(current.hasNext()){
			current = current.next();
			retString.append("-" + current.get());
		}
		return retString.toString();
	}

	//3-10
	@Override
	public LinkedListImpl<E> clone(){
		LinkedListImpl<E> retList = new LinkedListImpl<E>(this.element);
		if(this.hasNext() == true){
			retList.setNext(next.clone());
		}
		return retList;
	}
	
	public LinkedList<E> find(E ele) throws ObjectNotFoundException{
		LinkedList<E> l = this;
		while(l.hasNext()){
			l = l.next();
			if(l.get().equals(ele)){
				return l;
			}
		}
		throw new ObjectNotFoundException(ele.toString());
	}
}
