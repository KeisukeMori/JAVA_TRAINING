package ch21.ex21_07;

import java.util.ArrayList;

public class Stack<E> {

	private ArrayList<E> data = new ArrayList<E>();
	
	public boolean empty() {
		return data.isEmpty();
	}
	
	public E peek() {
		if(!data.isEmpty()) {
			return data.get(0);
		}
		return null;
	}
	
	public E pop() {
		if(!data.isEmpty()) {
			E  e = data.get(0);
			data.remove(0);
			return e;
		}
		return null;
	}
	
	public void push(E e) {
		data.add(data.size(), e);
	}
	
	public int search(Object e) {
		return data.indexOf(e) + 1;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(");
		for(E e : data){
			str.append(e.toString() + " ");
		}
		str.deleteCharAt(str.length()-1);
		str.append(")");
		return str.toString();
	}
}
