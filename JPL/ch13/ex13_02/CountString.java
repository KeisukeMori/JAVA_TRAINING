package ch13.ex13_02;

public class CountString {
	
	/*
	 * 文字列中に、指定の文字列が出現する回数を数える
	 */
	public int countString(String str, String key) {
		int count = 0;
		int begPos = str.indexOf(key);
		int keySize = key.length();
		
		if (begPos != -1) {
			int endPos = str.lastIndexOf(key);
			for (int i = begPos; i <= endPos; i++) {
				for (int j = 0; j < keySize; j++) {
					if (str.charAt(i) != key.charAt(j)) {
						break;
					}
					count ++;
				}
			}
		}
		return count;
	}
}
