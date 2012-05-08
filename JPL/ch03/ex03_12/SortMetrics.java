package ch03.ex03_12;

final class SortMetrics implements Cloneable {
    public long probeCnt,       // 単純なデータの値調査
                compareCnt,     // ２つの要素の比較
                swapCnt;        // ２つの要素の交換

    public void init() {
        probeCnt = swapCnt = compareCnt = 0;
    }

    public String toString() {
        return probeCnt + " probes " +
               compareCnt + " compares " +
               swapCnt + " swaps";
    }

    /** clone */
    public SortMetrics clone() {
        try {
            // デフォルトの仕組みで十分
            return (SortMetrics) super.clone(); 
        } catch (CloneNotSupportedException e) {
            // 起こりえない。このクラスと Object は複製できる
            throw new InternalError(e.toString());
        }
    }
}
