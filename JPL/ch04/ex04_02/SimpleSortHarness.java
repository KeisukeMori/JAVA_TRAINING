package ch04.ex04_02;

public class SimpleSortHarness extends SortHarnessImpl{

	protected void doSort() {
		exeSort();
	}

	private void exeSort() {
		for(int i = 0; i < getDataLength(); i++) {
			for(int j = i + 1; j < getDataLength(); j++) {
				if(compare(i, j) > 0) {
					swap(i, j);
				}
			}
		}
	}
}
