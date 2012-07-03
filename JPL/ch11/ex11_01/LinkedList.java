package ch11.ex11_01;

public class LinkedList<E> {
	public Object<E> head;
	
	public E getValue(int index) {
		Object<E> vObj = head;
		int i = 0;
		while (vObj != null) {
			if (i == index) {
				return vObj.getValue();
			}
			vObj = vObj.getNext();
			i++;
		}
		return null;
	}
	
	public void add(E val) {

		Object<E> vObj = head;
		Object<E> obj = new Object<E>(val);
		if (vObj == null) {
			head = obj;
			return;
		}		
		while (vObj != null) {
			if (vObj.getNext() == null) {
				vObj.setNext(obj);
				return;
			}
			vObj = vObj.getNext();
		}
	}

	
	static class Object<E> {
		private E value;
		private Object<E> next;
		
		public Object(E val) {
			value = val;
		}
		
		public E getValue() {
			return value;
		}
		
		public Object<E> getNext() {
			return next;
		}
		
		public void setNext(Object<E> nextItem) {
			next = nextItem;
		}
	}
}

