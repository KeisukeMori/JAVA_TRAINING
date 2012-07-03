package ch13.ex13_05;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuoInsertTest {

	@Test
	public void test1() {
		String str = "0123445666";
		String actualStr = QuoInsert.quoInsert(str);
		String expectedStr = "0,123,445,666";
		
		assertEquals(actualStr, expectedStr);
	}
	
	@Test
	public void test2() {
		String str = "181841181154";
		String actualStr = QuoInsert.quoInsert(str);
		String expectedStr = "181,841,181,154";
		
		assertEquals(actualStr, expectedStr);
	}

}

