package ch01.ex01_07;

public class ImprovedFibonacci {
    static final int MAX_INDEX = 9;  
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        String mark;
        
        System.out.println("1: " + lo);
        for (int i = MAX_INDEX; 1 < i; i--) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            
            System.out.println((MAX_INDEX - i + 2)  + ": " + hi + mark);
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}
