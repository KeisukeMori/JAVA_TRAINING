package ch03.ex03_02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class XTest extends X{
	//methodTest
	@Test
	public void testMethod() {
		assertEquals(0x00ff, xMask);
//		X x = new X();
		assertEquals(0x00ff, fullMask);
		
		traceStep();
		
		assertEquals(0x00ff, getXMask());
		
		assertEquals(4, getStep());		
	}
}
