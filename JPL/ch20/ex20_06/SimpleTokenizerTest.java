package ch20.ex20_06;

import java.io.IOException;
import java.io.StringReader;

public class SimpleTokenizerTest {

	private static String TEST_STRING =
		"valA = 3\n" +
		"valB = 4\n" +
		"valA + 5\n" +
		"valB - 2\n";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringReader reader = new StringReader(TEST_STRING);
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		try{
			tokenizer.analyze(reader);
			tokenizer.printResult();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
