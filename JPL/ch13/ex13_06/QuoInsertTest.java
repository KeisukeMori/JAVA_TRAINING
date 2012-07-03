package ch13.ex13_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuoInsertTest {

	@Test
	public void test1() {
		String str = "161416811868";
		int quo = 4;
		String actualStr = QuoInsert.quoInsert(str, quo, "/");
		String expectedStr = "1614/1681/1868";
		
		assertEquals(actualStr, expectedStr);
	}
	
	@Test
	public void test2() {
		String str = "181841181154";
		int quo = 10;
		String actualStr = QuoInsert.quoInsert(str, quo, "?");
		String expectedStr = "18?1841181154";
		
		assertEquals(actualStr, expectedStr);
	}

}

