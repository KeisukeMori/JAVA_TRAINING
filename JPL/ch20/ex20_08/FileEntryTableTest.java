package ch20.ex20_08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileEntryTableTest {

	private static final String TEST_FILE = "FileEntryTest.dat";
	
	public static void main(String[] args){
		File inputFile = new File(TEST_FILE);
		FileEntryTable table = new FileEntryTable();
		
		try{
			table.createEntry(inputFile);
			for(int i = 0; i < 10; i++){
				System.out.println("i:" + table.getEntryStrAtRnd());
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
