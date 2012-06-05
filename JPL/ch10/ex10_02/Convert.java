package ch10.ex10_02;


public class Convert {
		public String convertSpecialChar(String str) {

			String result = "";
			for (int i = 0; i < str.length(); i++) {	
	            switch (str.charAt(i)) {
	            case '\n':
					result += "\\n";
					break;

	            case '\t':
					result += "\\t";
					break;

	            case '\b':
					result += "\\b";
					break;
					
	            case '\r':
					result += "\\r";
					break;

	            case '\f':
					result += "\\f";
					break;

	            case '\\':
					result += "\\\\";
					break;

	            case '\"':
					result += "\\\"";
					break;

	            case '\'':
					result += "\\\'";
					break;

				default:
					result += str.charAt(i);
					break;
				}
			}		
			return result;
		}
		public static void main(String[] args) {
			Convert convert = new Convert();
			System.out.println(convert.convertSpecialChar("aa\t \'\" \""));
		}
	}