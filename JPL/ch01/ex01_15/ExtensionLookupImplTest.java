package ch01.ex01_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExtensionLookupImplTest {
	
	
	
	@Test
	public void testExtension() {
		ExtensionLookupImpl test = new ExtensionLookupImpl();
		test.names = new String[]{"abc", "def"};
		test.values = new Object[]{100, 200};
		
		Object addValue = 300;
		test.add(300);
		
		assertEquals(300, test.values[0]);
		
		Object[] remover = test.remove("abc");
		assertNull(null, remover[0]);

	}

}
