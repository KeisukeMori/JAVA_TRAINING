package ch21.ex21_04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements Iterator<String> {

	private Iterator<String> strings;
	private String nextShort;
	private final int maxLen;
	
	public ShortStrings(Iterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		nextShort = null;
	}
	

	public boolean hasNext() {
		if(nextShort != null) {
			return true;
		}
		while(strings.hasNext()) {
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
		return n;
	}


	public void remove() {
		throw new UnsupportedOperationException();
	}
}
