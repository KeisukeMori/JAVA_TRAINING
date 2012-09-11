package ch20.ex20_10;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import static java.io.StreamTokenizer.*;
import java.util.HashMap;
import java.util.Map;

public class WordsCount {

	private HashMap <String, Integer> counts = new HashMap <String, Integer>();

	
	public void result(){
		for(Map.Entry <String, Integer> entry : counts.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public void count(String str){
		Integer i;
		if(counts.containsKey(str)){
			i = counts.get(str);
			i++;
		}
		else {
			i = 1;
		}
		counts.put(str, i);
	}
	
	//入力ファイルを分析
	public void analyze(Reader reader) throws IOException{
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		
		int tokenKind = TT_EOF;
		//ファイルの終わりまで
		while((tokenKind = tokenizer.nextToken()) != TT_EOF){
			switch(tokenKind){
			case TT_NUMBER:
				String num = String.valueOf(tokenizer.nval);
				count(num);
				break;
			case TT_WORD:
				String value = tokenizer.sval;
				count(value);
				break;
			default:
				break;
			}
		}
	}
	
}
