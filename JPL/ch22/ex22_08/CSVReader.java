package ch22.ex22_08;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
	
	private static final String LINE_SEPARATOR_PATTERN = "(\r\n|[\n\r\u2028\u2029\u0085])";

	public static List<String[]> readCSVTable(Readable source, int cellsNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		if(cellsNum < 1) {
			throw new IllegalArgumentException("cellNums must be lager than 1.");
		}
		String exp = "^([^,]*)";
		for(int i = 1; i < cellsNum; i++) {
			exp += ",([^,]*)";
		}
		exp += "$";
		Pattern pattern = Pattern.compile(exp, Pattern.MULTILINE);
		
		while(in.hasNextLine()) {
			String line = in.findInLine(pattern);
			if(line != null){
				String[] cells = new String[cellsNum];
				MatchResult match = in.match();
				for(int i = 0; i < cellsNum; i++) {
					cells[i] = match.group(i + 1);
				}
				vals.add(cells);
				in.nextLine();
			}
			else {
				String spaceExp = "[\\s]*";
				while(in.hasNextLine()) {
					String str = in.nextLine();
					if(!Pattern.matches(spaceExp, str)) {
						throw new IOException("input format error");
					}
				}
				break;
			}
		}
		
		IOException ex = in.ioException();
		if(ex != null){
			throw ex;
		}
		return vals;
	}
	
	
	public static void main(String[] args) {		
		String input = "abc,123,def,1,ten \n abc,hello,test,2,two\n";
		StringReader reader = new StringReader(input);
		
		try {
			List<String[]> result = CSVReader.readCSVTable(reader, 5);
			for(int j = 0; j < result.size(); j++){
				StringBuilder str = new StringBuilder();
				str.append(j + ":");
				for(int i = 0; i < 5; i++){
					str.append(result.get(j)[i] + ",");
				}
				str.deleteCharAt(str.length()-1);
				System.out.println(str);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
