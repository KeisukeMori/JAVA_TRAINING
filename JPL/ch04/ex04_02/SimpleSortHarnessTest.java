package ch04.ex04_02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SimpleSortHarnessTest {
	Object[] testNum = {0, 3, 7, 9, 3.17, 100, 5.5, "17"};
	final Object[] SORT_NUM = {0, 100, 17, 3, 3.17, 5.5, 7, 9};

	@Test
	public void testDoSort() {
		SimpleSortHarness Sort = new SimpleSortHarness();
		SortMetrics metrics = Sort.sort(testNum);
		System.out.println(metrics);
        for (int i = 0; i < testNum.length; i++) {
            System.out.println("\t" + testNum[i]);
        }

		for (int i = 0; i < testNum.length; i++) {
			assertEquals(SORT_NUM[i].toString(), testNum[i].toString());
		}
	}
}

