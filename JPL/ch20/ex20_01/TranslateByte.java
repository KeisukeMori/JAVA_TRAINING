package ch20.ex20_01;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	public static void main(String [] args) {
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream("test.dat");

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			TranslateByte.translateStream(fileInput, System.out, (byte)'a', (byte)'A');
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close
			if(fileInput != null) {
				try {
					fileInput.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 変換実施
	public static void translateStream(InputStream in, OutputStream out, byte from, byte to)
	throws IOException {
		int b;
		while((b = in.read()) != -1){
			out.write(b == from ? to : b);
		}
		out.flush();
	}
}
