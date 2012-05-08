package ch03.ex03_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorAttrTest {
	//methodTest
	@Test
	public void testMethod() {
		ColorAttr ca = new ColorAttr("test","red");
		ColorAttr ca2 = new ColorAttr("test","red");
		
		assertEquals(ca.hashCode(), ca2.hashCode());
		assertNotSame(ca, ca2);
	}
}
