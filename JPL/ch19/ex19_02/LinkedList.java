package ch19.ex19_02;

/**
 * リンクドリスト
 * @author kmori
 */
public class LinkedList implements Cloneable{
	/**
	 * リンクドリストの要素に保存するデータ
	 */
	private Object element;
	/**
	 * リンクドリストの要素が指す次の要素
	 */
	private LinkedList next;

	/**
	 * テスト用メイン関数、適当なリンクドリストを生成し、表示する。
	 * @param args プログラムの引数、実行時には無視される。
	 */
	public static void main(String [] args){
		LinkedList l = new LinkedList(new String[]{"a", "b", "c", "d",});
		System.out.println(l);
		System.out.println(l.size());
	}

	/**
	 * コンストラクタ、リンクドリストの単一の要素を生成する。
	 * @param element 要素に含む値
	 */
	public LinkedList(Object element){
		this.element = element;
	}

	/**
	 * コンストラクタ、リンクドリストの単一の要素を生成し、同時にその要素の次の要素を設定する。
	 * @param element 要素に含む値
	 * @param next 次の要素
	 */
	public LinkedList(Object element, LinkedList next){
		this(element);
		this.next = next;
	}


	/**
	 * コンストラクタ、リンクドリストを与えられた配列の順に生成する。
	 * @param elementsArray リンクドリストの要素として含む値の配列
	 */
	public LinkedList(Object... elementsArray){
		if(elementsArray == null || elementsArray.length < 1){
			throw new IllegalArgumentException();
		}
		LinkedList nextElement = null;
		for(int i = elementsArray.length -1; i > 0; i--){
			LinkedList element = new LinkedList(elementsArray[i], nextElement);
			nextElement = element;
		}
		this.element = elementsArray[0];
		this.setNext(nextElement);
	}

	/**
	 * この要素の次の要素を設定する。
	 * @param listElement 次の要素
	 */
	public void setNext(LinkedList listElement){
		this.next = listElement;
	}

	/**
	 * この要素の次の要素を取得する。
	 * @return 次の要素
	 */
	public LinkedList next(){
		return next;
	}
	/**
	 * この要素が次の要素を持つか取得する。
	 * @return 次の要素を持つか否か
	 */
	public boolean hasNext(){
		return next != null;
	}
	
	/**
	 * この要素の値を取得する。
	 * @return 値
	 */
	public Object get(){
		return element;
	}
	
	/**
	 * この要素の値を設定する。
	 * @param element
	 */
	public void set(Object element){
		this.element = element;
	}
	
	/**
	 * この要素以降に繋がる要素の数を取得する。
	 * @return 要素の数。
	 */
	public int size(){
		LinkedList current = this;
		int size = 1;

		while(current.hasNext()){
			current = current.next();
			size++;
		}
		return size;
	}

	/**
	 * この要素が繋がるリストの最後に、新たな要素を追加する。
	 * @param element 追加する要素の値。
	 */
	public void addLast(Object element){
		LinkedList l = this;
		while(l.hasNext()){
			l = l.next();
		}
		l.next = new LinkedList(element);
	}

	//from 2-11
	@Override
	public String toString(){
		StringBuffer retString = new StringBuffer();
		LinkedList current = this;

		retString.append(this.element);
		while(current.hasNext()){
			current = current.next();
			retString.append("-" + current.get());
		}
		return retString.toString();
	}

}
