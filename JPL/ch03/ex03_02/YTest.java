package ch03.ex03_02;

import static org.junit.Assert.*;
import org.junit.Test;


public class YTest extends Y{
	//methodTest
	@Test
	public void testMethod() {
		assertEquals(0x00ff, xMask);
		assertEquals(0xff00, yMask);
		assertEquals(0xffff, fullMask);
		
		traceStep();
		
		assertEquals(0xff00, getYMask());
		
		assertEquals(6, getStep());		
	}
}
