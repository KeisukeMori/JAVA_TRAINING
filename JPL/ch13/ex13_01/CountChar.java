package ch13.ex13_01;

public class CountChar {
	public int countChar(String str, char ch) {
		int count = 0;
		
		if (str == null) {
			throw new NullPointerException();
		}
		
        for (int i = 0; i < str.length(); i++) {
        	if( str.charAt(i) == ch ) {
        		count++;
        	}
        }
        return count;
	}
}
