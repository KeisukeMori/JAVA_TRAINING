package ch24.ex24_01;

import java.util.ListResourceBundle;

/**
 * 
 * @author kmori
 *
 */
public class GlobalRes extends ListResourceBundle{

	public static final String HELLO = "hello";
	public static final String GOODBYE = "goodbye";
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

	private static final Object[][] contents = {
		{GlobalRes.HELLO, "Ciao"},
		{GlobalRes.GOODBYE, "Ciao"},
	};
}
