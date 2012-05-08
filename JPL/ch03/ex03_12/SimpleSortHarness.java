package ch03.ex03_12;

public class SimpleSortHarness extends SortHarness{

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
