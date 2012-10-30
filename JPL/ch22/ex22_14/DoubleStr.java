package ch22.ex22_14;

public class DoubleStr {

	private static final String SEPARATOR = " ";
	
	public static void main(String[] args){
		System.out.println(DoubleStr.parseAndCalc("1.0 2.0 3.0"));
	}
	
	public static double parseAndCalc(String str){
		String[] valueStrs = str.split(SEPARATOR);
		if(valueStrs.length == 0){
			throw new IllegalArgumentException("str dosen't contain any values.");
		}
		double sum = 0;
		for(int i = 0 ; i < valueStrs.length ; i++){
			sum += Double.parseDouble(valueStrs[i]);
		}
		return sum;
	}
}

