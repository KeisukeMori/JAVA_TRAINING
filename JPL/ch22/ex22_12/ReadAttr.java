package ch22.ex22_12;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import ch22.ex22_09.CSVScanner;


public class ReadAttr {
	private static final String LINE_SEPARATOR_PATTERN = "(\r\n|[\n\r\u2028\u2029\u0085])";
	
	public static void main(String[] args){	
		String exp = "^(.*?)=(.*)";
		Pattern pattern = Pattern.compile(exp, Pattern.MULTILINE);
		System.out.println(pattern.matches(exp, "test=hello"));
		
		String input = "test=hello\nabc=cde\nfff=sss\n";
		StringReader reader = new StringReader(input);
		
		try {
			List<Attr<String>> result = ReadAttr.scanAttrs(reader);
			for(int j = 0; j < result.size(); j++){
				StringBuilder str = new StringBuilder();
				str.append(j + ":");
				str.append(result.get(j).getName() + "," + result.get(j).getValue());
				System.out.println(str);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static List<Attr<String>> scanAttrs(Readable source) throws IOException{
		Scanner in = new Scanner(source);
		List<Attr<String>> attrs = new LinkedList<Attr<String>>();
		String exp = "(.*?)=(.*)$";
		Pattern pattern = Pattern.compile(exp, Pattern.MULTILINE);
		
		while(in.hasNextLine()){
			String line = in.findInLine(pattern);
			if(line != null){
				MatchResult m = in.match();
				attrs.add(new Attr<String>(m.group(1), m.group(2)));
				in.nextLine();

			}
			else {
				String spaceExp = "[\\s]*";
				while(in.hasNextLine()){
					String str = in.nextLine();
					if(!Pattern.matches(spaceExp, str)){
						//�t�@�C���̓r���Ƀt�H�[�}�b�g�̂������ȍs���������B
						throw new IOException("input format error:" +str);
					}
				}
				break;
			}
		}
		
		IOException ex = in.ioException();
		if(ex != null){
			throw ex;
		}
		return attrs;
	}
	
	
	
}
