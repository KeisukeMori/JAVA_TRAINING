package ch01.ex01_10;

public class ImprovedFibonacci {

    static final int MAX_INDEX = 9;
    
    static class Result {
        public int value;
        public boolean is_even; //値が偶数かどうか
    }
    
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        
        Result[] results = new Result[MAX_INDEX];
        
        for (int i = 0; i < results.length; i++) {
            results[i] = new Result();
        }
        
        results[0].value = lo;
        results[0].is_even = (lo % 2 == 0);
        
        for (int i = 2; i <= MAX_INDEX; i++) {
            results[i - 1].value = hi;
            results[i - 1].is_even = (hi % 2 == 0);
            
            hi = lo + hi;
            lo = hi - lo;
        }
        
        String mark = null;
        for (int i = 0; i < results.length; i++) {
            if (results[i].is_even) {
                mark = " *";
            } else {
                mark = "";
            }
              
            System.out.println((i + 1) + ":" + results[i].value + mark);
        }
    }
}