package ch22.ex22_10;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import ch22.ex22_09.CSVScanner;


public class ReadToken {
	private static final String LINE_SEPARATOR_PATTERN = "(\r\n|[\n\r\u2028\u2029\u0085])";
	
	public static List<String> readToken(Readable source) throws IOException{
		Scanner in = new Scanner(source);
		List<String> vals = new LinkedList<String>();
		in.useDelimiter("(\\s)|(#.*"+LINE_SEPARATOR_PATTERN+")");
		
		while(in.hasNext()){
			String token = in.next();
			vals.add(token);
		}
		
		IOException ex = in.ioException();
		if(ex != null){
			throw ex;
		}
		return vals;
	}
	
	
	
	public static void main(String[] args){			
		String input = "test test.#-- comment --\n I am keisuke mori. \n test end.";
		StringReader reader = new StringReader(input);
		
		try {
			List<String> result = ReadToken.readToken(reader);
			for(int j = 0; j < result.size(); j++) {
				System.out.println(result.get(j));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}	
}
