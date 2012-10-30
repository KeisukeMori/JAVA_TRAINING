package ch22.ex22_04;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

public class NotifyAttributed<E> extends Observable implements IAtributted<E> {

	private LinkedList<Attr<E>> attrList = new LinkedList<Attr<E>>();
	
	public void Add(Attr<E> attr) {
		Attr<E> exists = find(attr.getName());
		if(exists != null){
			attrList.remove(exists);
		}
		attrList.add(attr);
		setChanged();
		notifyObservers(attr);
	}

	public Attr<E> find(String attrName) {
		for(Attr<E> attr : attrList){
			if(attr.getName().equals(attrName)){
				return attr;
			}
		}
		return null;

	}

	public Attr<E> remove(String attrName) {
		Attr<E> exists = find(attrName);
		if(exists == null){
			return null;
		}
		attrList.remove(exists);
		setChanged();
		notifyObservers(exists);
		return exists;
	}

	public Iterator<Attr<E>> attrs() {
		return attrList.iterator();
	}

}
