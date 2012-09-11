package ch20.ex20_02;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TranslateByteFilter extends FilterInputStream{

	private byte from;
	private byte to;
	
	protected TranslateByteFilter(InputStream in, byte from, byte to) {
		super(in);
		this.from = from;
		this.to = to;
	}
	
	@Override
	public int read() throws IOException{
		int b = super.read();
		if(b != -1) {
			b = ( (byte)b == from ? to : b );
		}
		return b;
	}

}
