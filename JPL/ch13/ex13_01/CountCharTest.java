package ch13.ex13_01;

import static org.junit.Assert.*;
import org.junit.Test;

public class CountCharTest {

	@Test
	public void countCharTest1() {
		String str = "xxxxxx";
		char character = 'a';
		
		CountChar count = new CountChar();
		int actualCount = count.countChar(str, character);
		int expectedCount = 0;
		
		assertEquals(actualCount, expectedCount);
	}
	
	@Test
	public void countCharTest2() {
		String str = "abcdefgh";
		char character = 'c';
		
		CountChar count = new CountChar();
		int actualCount = count.countChar(str, character);
		int expectedCount = 1;
		
		assertEquals(actualCount, expectedCount);
	}
	
	@Test
	public void countCharTest3() {
		String str = "aaaa";
		char character = 'a';
		
		CountChar count = new CountChar();
		int actualCount = count.countChar(str, character);
		int expectedCount = 4;
		
		assertEquals(actualCount, expectedCount);
	}
}
