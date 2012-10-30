package ch22.ex22_13;

import static java.io.StreamTokenizer.TT_EOF;
import static java.io.StreamTokenizer.TT_NUMBER;
import static java.io.StreamTokenizer.TT_WORD;
import static java.io.StreamTokenizer.TT_EOL;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVReader {

	/**
	 * @param args
	 */
	public static void main(String[] args){		
		String input = "test,hello ,abc,1,one\nabc,hello,test,2,two\n";
		StringReader reader = new StringReader(input);
		
		try {
			List<String[]> result = CSVReader.read(reader, 5);
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

	public static List<String[]> read(Reader reader, int rowNum) throws IOException{
		LinkedList<String[]> tokens = new LinkedList<String[]>();
		
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		tokenizer.eolIsSignificant(true);
		
		tokenizer.whitespaceChars(',', ',');
		tokenizer.wordChars(' ', ' ');
		
		int tokenKind = TT_EOF;
		String[] row = new String[rowNum];
		int rowItemCount = 0;
		while((tokenKind = tokenizer.nextToken()) != TT_EOF){
			switch(tokenKind){
			case TT_NUMBER:
				row[rowItemCount] = String.valueOf(tokenizer.nval);
				rowItemCount++;
				if(rowItemCount > rowNum){
					throw new IOException("too much args");
				}
				break;
			case TT_WORD:
				row[rowItemCount] = tokenizer.sval;
				rowItemCount++;
				if(rowItemCount > rowNum){
					throw new IOException("too much args");
				}
				break;
			case TT_EOL:
				if(rowItemCount != rowNum){
					throw new IOException("too few args");					
				}
				tokens.add(row);
				row = new String[rowNum];
				rowItemCount = 0;
				break;
			default:
				break;
			}
		}
		return tokens;
	}
}
