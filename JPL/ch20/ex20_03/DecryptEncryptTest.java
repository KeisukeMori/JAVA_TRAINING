package ch20.ex20_03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class DecryptEncryptTest {

	private static String TEST_STRING = "this is test";
	
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream output = null;
		EncryptOutputStream outputStream = null;
		ByteArrayInputStream input = null;
		DecryptInputStream inputStream = null;
		byte [] buf;
		try { 
			output = new ByteArrayOutputStream();
			outputStream = new EncryptOutputStream(output);
			outputStream.write(TEST_STRING.getBytes());
			outputStream.flush();
			
			buf = output.toByteArray();
			System.out.println("encrypt:" + new String(buf));

			input = new ByteArrayInputStream(buf);
			inputStream = new DecryptInputStream(input);
			
			int b = -1;
			System.out.print("decrypt:");
			while((b = inputStream.read()) != -1) {
				System.out.print((char)b);
			}
			System.out.println();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null) {
				output.close();
			}
			if(outputStream != null) {
				outputStream.close();
			}
			if(input != null) {
				input.close();
			}
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}
}
