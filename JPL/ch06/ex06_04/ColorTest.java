package ch06.ex06_04;

import static org.junit.Assert.*;
import org.junit.Test;

public class ColorTest {

	//methodTest
	@Test
	public void testMethod() {
		assertEquals("GREEN", (Color.GREEN.name));
		assertEquals("YELLOW", (Color.YELLOW.name));
		assertEquals("RED", (Color.RED.name));
	}
}
