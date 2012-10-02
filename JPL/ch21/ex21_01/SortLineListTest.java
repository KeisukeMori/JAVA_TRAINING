package ch21.ex21_01;

import java.io.File;

public class SortLineListTest {

	private static final String TEST_FILE = "sortTest.dat";

	
	public static void main(String[] args) {
		File testFile = new File(TEST_FILE);
		SortLineList lineList = new SortLineList();
		lineList.readFile(testFile);
		lineList.printLines();
	}

}
