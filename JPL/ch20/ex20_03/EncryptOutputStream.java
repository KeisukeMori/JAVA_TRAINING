package ch20.ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
	private byte key = (byte)0000;
	
	protected EncryptOutputStream(OutputStream out) {
		super(out);
	}

	public void write(int b) throws IOException {
		b ^= key;
		super.write(b);
	}
}
