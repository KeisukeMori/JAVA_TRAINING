package ch03.ex03_05;

import static org.junit.Assert.*;
import org.junit.Test;

public class MethodBenchmarkTest extends MethodBenchmark{

	//methodTest
	@Test
	public void testMethod() {
		int count = 400;
		MethodBenchmark benchmark = new MethodBenchmark();
		benchmark.setLoopCounter(1000);

		long time = benchmark.repeat(count);
		
		assertEquals(400, count);
	}
}
