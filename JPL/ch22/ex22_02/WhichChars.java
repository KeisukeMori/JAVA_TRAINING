package ch22.ex22_02;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

public class WhichChars {
	private BitSet used = new BitSet();
	private HashSet<Character> charSet = new HashSet<Character>();
	
	public static void main(String [] args) {
		System.out.println(new WhichChars("test55").toString());
	}
	
	public WhichChars(String str){
		for(int i = 0; i < str.length(); i++) {
			if(!charSet.contains(str.charAt(i))) {
				charSet.add(str.charAt(i));
			}
//			used.set(str.charAt(i));
		}
	}
	
	public String toString(){
		String str = "(";

		for(Iterator<Character> it = charSet.iterator(); it.hasNext(); ){
			str += it.next();
		}
		return str + ")";
	}
}
