package ch10.ex10_01;

public class Convert {
	public String convertSpecialChar(String str) {

		String result = "";
		for (int i = 0; i < str.length(); i++) {
			
			if (str.charAt(i) == '\n') {
				result += "\\n";

			} else if (str.charAt(i) == '\t') {
				result += "\\t";

			} else if (str.charAt(i) == '\b') {
				result += "\\b";

			} else if (str.charAt(i) == '\r') {
				result += "\\r";

			} else if (str.charAt(i) == '\f') {
				result += "\\f";

			} else if (str.charAt(i) == '\\') {
				result += "\\\\";

			} else if (str.charAt(i) == '\"') {
				result += "\\\"";

			} else if (str.charAt(i) == '\'') {
				result += "\\\'";

			} else {
				result += str.charAt(i);
			}
		}		
		return result;
	}
	public static void main(String[] args) {
		Convert convert = new Convert();
		System.out.println(convert.convertSpecialChar("aa\t \'\" \""));
	}
}


