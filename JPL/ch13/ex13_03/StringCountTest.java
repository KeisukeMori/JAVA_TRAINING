package ch13.ex13_03;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringCountTest {

	@Test
	public void test1() {
		String str = "morimori";
		char start = 'm';
		char end = 'i';
		
		String[] actualStrings = StringCount.delimitedString(str, start, end);
		String[] expectedStrings = {"mori", "mori"};
		
		for (int i = 0; i < expectedStrings.length; i++) {
			String actualStr = actualStrings[i];
			String expectedStr = expectedStrings[i];
			assertEquals(actualStr, expectedStr);
		}
	}
	
	@Test
	public void test2() {
		String str = "morimori";
		char start = 'm';
		char end = 'm';
		
		String[] actualStrings = StringCount.delimitedString(str, start, end);
		String[] expectedStrings = {"morim"};
		
		for (int i = 0; i < expectedStrings.length; i++) {
			String actualStr = actualStrings[i];
			String expectedStr = expectedStrings[i];
			assertEquals(actualStr, expectedStr);
		}
	}
	
	@Test
	public void test3() {
		String str = "morimori";
		char start = 'm';
		char end = 'z';
		
		String[] actualStrings = StringCount.delimitedString(str, start, end);
		String[] expectedStrings = {"morimori"};
		
		for (int i = 0; i < expectedStrings.length; i++) {
			String actualStr = actualStrings[i];
			String expectedStr = expectedStrings[i];
			assertEquals(actualStr, expectedStr);
		}
	}

}

