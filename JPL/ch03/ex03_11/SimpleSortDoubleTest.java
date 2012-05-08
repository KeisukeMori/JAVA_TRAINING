package ch03.ex03_11;

import static org.junit.Assert.*;

import org.junit.Test;


public class SimpleSortDoubleTest {
	double[] testNum = {0, 3, 7, 9, 3.17, 100, 5.5, 17};
	final double[] SORT_NUM = {0, 3, 3.17, 5.5, 7, 9, 17, 100};

	@Test
	public void testDoSort() {
		SortDouble Sort = new SimpleSortDouble();
		SortMetrics metrics = Sort.sort(testNum);
		System.out.println(metrics);
		for (int i = 0; i < testNum.length; i++) {
			assertEquals(new Double(SORT_NUM[i]), new Double(testNum[i]));
		}
	}
}
