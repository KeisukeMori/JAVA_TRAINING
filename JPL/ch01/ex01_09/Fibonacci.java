package ch01.ex01_09;

public class Fibonacci {
	
    static final String TITLE = "フィボナッチ数列";
    static final int MAX_INDEX = 9;
    public static void main(String[] args) {
        int lo = 1, hi = 1;
        int[] array = new int[MAX_INDEX];
        
        System.out.println(TITLE);
        
        array[0] = lo;
        for (int i = 2; i <= MAX_INDEX; i++) {
            array[i - 1] = hi;
            hi = lo + hi;
            lo = hi - lo;
        }
        
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + ": " + array[i]);
        }
    }
}