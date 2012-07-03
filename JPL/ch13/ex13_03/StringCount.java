package ch13.ex13_03;

public class StringCount {
	
	public static String[] delimitedString(String from, char start, char end) {
		int startPos = -1;
		int endPos = 0;
		int index = 0;
		String[] results = new String[100];
	
		//値が見つかる間
		while (from.indexOf(start, startPos + 1) > -1) {
		    startPos = from.indexOf(start, startPos + 1);
			endPos = from.indexOf(end, startPos + 1);
			if (startPos == -1) {
				break;
			} else if (endPos == -1) {
				results[index] = from.substring(startPos);
				break;
			} else if (startPos > endPos) {
				break;
			} else {
				results[index] = from.substring(startPos, endPos + 1);
				index ++;
			}
		}
		return results;
	}
}