package ch20.ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class ReadLineFilter extends FilterReader {

	protected ReadLineFilter(Reader reader) {
		super(reader);
	}

	public String readLine() throws IOException {
		StringBuilder strBuilder = new StringBuilder();
		int b = -1;

		while((b = super.read()) != -1) {
			switch((byte)b) {
			case '\r':
				strBuilder.append((char)b);
				if((byte)(b = super.read()) == '\n') {
					strBuilder.append((char)b);
				}
				return strBuilder.toString();
			case '\n':
				strBuilder.append((char)b);
				return strBuilder.toString();

			}
		}
		return strBuilder.toString();
	}
	
}
