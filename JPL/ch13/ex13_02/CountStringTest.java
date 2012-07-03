package ch13.ex13_02;

import static org.junit.Assert.*;
import org.junit.Test;

public class CountStringTest {
	
	@Test
	public void countStrTest1() {
		String str = "mori mori keisuke";
		String key = "mori";
		
		CountString count = new CountString();
		int actualCount = count.countString(str, key);
		int expectedCount = 2;
		
		assertEquals(actualCount, expectedCount);
	}
	
	@Test
	public void countStrTest2() {
		String str = "abcdefghijklmnopqrstuvwxyz";
		String key = "jk";
		
		CountString count = new CountString();
		int actualCount = count.countString(str, key);
		int expectedCount = 1;
		
		assertEquals(actualCount, expectedCount);
	}
	

}