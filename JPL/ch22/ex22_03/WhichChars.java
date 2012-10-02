package ch22.ex22_03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class WhichChars {
	private HashMap<Byte, BitSet> maps = new HashMap<Byte, BitSet>();
	
	public static void main(String [] args) {
		System.out.println(new WhichChars("test55").toString());
	}
	
	public WhichChars(String str) {
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Byte bottomByte = (byte)c;
			Byte topByte = (byte)(8 >> c);
			BitSet used = null;
			
			if(maps.containsKey(topByte)) {
				used = maps.get(topByte);
			}
			else{
				used = new BitSet();
				maps.put(topByte, used);
			}
			used.set(bottomByte);
		}
	}
	
	public String toString(){
		String str = "[";

		for(Map.Entry<Byte, BitSet> entry : maps.entrySet()){
			byte upperByte = entry.getKey();
			BitSet used = entry.getValue();
			for(int i = used.nextSetBit(0); i >= 0; i = used.nextSetBit(i+1)) {
				char c = (char)upperByte;
				c <<= 8;
				c |= i;
				str += (char)i;
			}
		}

		return str + "]";
	}
}
