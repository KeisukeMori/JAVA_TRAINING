package ch11.ex11_01;

import static org.junit.Assert.*;

import org.junit.Test;


public class LinkedListTest {

	@Test
	public void addTest() {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("ab");
		String test = "ab";
		String actual = list.head.getValue();
		assertEquals(test, actual);
	}
	
	@Test
	public void getTest() {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("xyz");
		
	    String expected = "xyz";
		String test = null;
		try {
			test = list.getValue(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected, test);
	}
}

