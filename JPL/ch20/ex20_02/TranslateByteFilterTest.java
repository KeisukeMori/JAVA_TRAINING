package ch20.ex20_02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ch20.ex20_01.TranslateByte;

public class TranslateByteFilterTest {


	public static void main(String[] args) {
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream("test.dat");
			TranslateByteFilter filter = new TranslateByteFilter(fileIn, (byte)'a', (byte)'A');
			int b = -1;
			while((b = filter.read()) != -1){
				System.out.print((char)b);
			}
			System.out.println();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileIn != null) {
				try {
					fileIn.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
