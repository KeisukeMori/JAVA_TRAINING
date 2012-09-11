package ch20.ex20_05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FindWord {
	
	public static void find(String word, File file) throws FileNotFoundException, IOException {
		FileReader fileReader = null;
		LineNumberReader numReader = null;
		try {
			fileReader = new FileReader(file);
			numReader = new LineNumberReader(fileReader);
			
			String line = null;
			while((line = numReader.readLine()) != null) {
				if(line.contains(word)){
					System.out.println(numReader.getLineNumber() + 1 + ":" + line);
				}
			}
		} finally {
			if(fileReader != null){
				fileReader.close();
			}
			if(numReader != null){
				numReader.close();
			}
		}
	}
}
