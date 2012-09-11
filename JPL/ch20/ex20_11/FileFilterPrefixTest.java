package ch20.ex20_11;

import java.io.File;

public class FileFilterPrefixTest {
	private static final String DIR = "C:\\Windows";
	public static void main(String [] args){
		File file = new File(DIR);
		FileFilterPrefix filter = new FileFilterPrefix(file, ".ini");
		filter.printFileList();
	}
}
