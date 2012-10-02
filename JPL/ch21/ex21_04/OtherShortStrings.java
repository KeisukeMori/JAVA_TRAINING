package ch21.ex21_04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OtherShortStrings implements ListIterator<String>{

	private ListIterator<String> strings;
	private String prevShort;
	private int prevIndex;
	private String nextShort;
	private int nextIndex;
	private int maxLen;

	public OtherShortStrings(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
	}
	

	public void add(String e) {
		throw new UnsupportedOperationException();
	}


	public boolean hasNext() {
		if(nextShort != null) {
			return true;
		}
		while(strings.hasNext()) {
			nextIndex = strings.nextIndex();
			nextShort = strings.next();
			if(nextShort.length() <= maxLen) {
				return true;
			}
		}
		nextShort = null;
		return false;
	}


	public String next() {
		if(nextShort == null && !hasNext()) {
			throw new NoSuchElementException();
		}
		String n = nextShort;
		nextShort = null;
		nextIndex = -1;
		return n;
	}


	public void remove() {
		throw new UnsupportedOperationException();
	}


	public boolean hasPrevious() {
		if(prevShort != null){
			return true;
		}
		while(strings.hasPrevious()) {
			prevIndex = strings.previousIndex();
			prevShort = strings.previous();
			if(prevShort.length() <= maxLen) {
				return true;
			}
		}
		prevShort = null;
		return false;
	}


	public int nextIndex() {
		if(nextIndex == -1 && !hasPrevious()) {
			return -1;
		}
		return nextIndex;
	}


	public String previous() {
		if(prevShort == null && !hasPrevious()) {
			throw new NoSuchElementException();
		}
		String n = prevShort;
		prevShort = null;
		prevIndex = -1;
		return n;
	}


	public int previousIndex() {
		if(prevIndex == -1 && !hasPrevious()) {
			return -1;
		}
		return prevIndex;
	}


	public void set(String e) {
		throw new UnsupportedOperationException();
	}

}
