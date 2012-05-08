package ch03.ex03_12;

abstract class SortHarness {
    private Object[] values; 
    private final SortMetrics curMetrics = new SortMetrics();


    public final SortMetrics sort(Object[] data) {
        values = data;
        curMetrics.init();
        doSort();
        return getMetrics();
    }   

    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }


    protected final int getDataLength() {
        return values.length;
    }


    protected final Object probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }


    protected final int compare(int i, int j) {
        curMetrics.compareCnt++;
        Object obj1 = values[i];
        Object obj2 = values[j];
        return obj1.toString().compareTo(obj2.toString());
    }
    
    /** 拡張したクラスが要素を交換するため */
    protected final void swap(int i, int j) {
        curMetrics.swapCnt++;
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /** 拡張したクラスが実装する -- ソートするのに使用される */
    protected abstract void doSort();
}
