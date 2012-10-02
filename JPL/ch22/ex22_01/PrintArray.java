package ch22.ex22_01;

import java.util.Formatter;

public class PrintArray {
	
	private static final int LINE_VAL = 80;
	
	public static void display(double [] array, int columnNums) {
		String formatStr = "% ." + columnNums + "g ";

		int columnItemCounts = LINE_VAL / (columnNums + 3);
		
		StringBuilder printStr = new StringBuilder();
		Formatter format = new Formatter(printStr);
		for(int i = 0; i < array.length; i++) {
			
			format.format(formatStr, array[i]);
			if(i % columnItemCounts == 0){
				printStr.append('\n');
			}
		}
		System.out.println(printStr);
	}
}
