package ch20.ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	private byte key = (byte)0000;
	
	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	public int read() throws IOException {
		int b = super.read();
		if (b != -1) {
			return b ^ key;
		}
		return b;
	}
}
