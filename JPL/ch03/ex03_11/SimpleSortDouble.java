package ch03.ex03_11;



/**
 * 
 * ソートメソッドが空で呼ばれるとセキュリティホールが発生するので
 * 一度しか呼ばれないようにbooleanで制御します。
 * @author keisukemori
 *
 */
public class SimpleSortDouble extends SortDouble{
	private boolean call = false;

	protected void doSort() {
		double tmp[]={0};

		if(call) {
			return;
		} else {
			call=true;
			exeSort();
			super.sort(tmp);
		}
	}
	
	private void exeSort() {
		for(int i = 0; i < getDataLength(); i++) {
			for(int j = i + 1; j < getDataLength(); j++) {
				if(compare(i, j)>0) {
					swap(i, j);
				}
			}
		}
	}
}
