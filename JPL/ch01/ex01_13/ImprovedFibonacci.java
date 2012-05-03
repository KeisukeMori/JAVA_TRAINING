package ch01.ex01_13;

public class ImprovedFibonacci {
    static final int MAX_INDEX = 9;
    
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        String mark;
        String message;
        
        message = "1: " + lo;
        System.out.printf(message + "%n");
        for (int i = 2; i <= MAX_INDEX; i++) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            message = i + ": " + hi + mark;
            System.out.printf(message + "%n");
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}