package ch20.ex20_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordsCountTest {
	private static final String TEST_FILE = "words.dat";
	public static void main(String[] args){
		WordsCount count = new WordsCount();
		FileReader reader = null;
		try{
			reader = new FileReader(new File(TEST_FILE));
			count.analyze(reader);
			count.result();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			if(reader != null){
				try{
					reader.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
