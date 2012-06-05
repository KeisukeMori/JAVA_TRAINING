package ch10.ex10_05;

public class CharValue {
	
	    static String showChars(char start, char end) {
	    	String filledStr = "";
			char min, max;
			min = (start < end ? start : end);
			max = (start < end ? end : start);
			int x = 0;
			while (min + x <= max) {
				filledStr += (char)(min + x);
				x++;
				}
	    	return filledStr;
	    	}
	    
	    public static void main(String[] args) {
	    System.out.println(CharValue.showChars('a', 'x'));
		System.out.println(CharValue.showChars('y', 'Y'));
	}
}

