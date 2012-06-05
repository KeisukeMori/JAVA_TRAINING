package ch09.ex09_02;

public class BitCheck {

	public int originalCountBit(int val) {
		int bit = 0;
		while(val != 0) { 
			if((val & 1) != 0) 
				bit++; 
			val >>= 1; 
		} 
		return bit; 

	} 

	// "ハッカーのたのしみ"から
	public int hackerCountBit(int val) {
		val = (val & 0x55555555) + ((val >> 1) & 0x55555555); 
		val = (val & 0x33333333) + ((val >> 2) & 0x33333333); 
		val = (val & 0x0f0f0f0f) + ((val >> 4) & 0x0f0f0f0f); 
		val = (val & 0x00ff00ff) + ((val >> 8) & 0x00ff00ff); 
		val = (val & 0x0000ffff) + ((val >>16) & 0x0000ffff); 
		return val;
	}


	public static void main(String[] args) {
		BitCheck cal = new BitCheck();
		int val = 55555555;
		System.out.println("original: " + cal.originalCountBit(val));
		System.out.println("hacker: " + cal.hackerCountBit(val));
		System.out.println("api: " + Integer.bitCount(val));
	}
}


