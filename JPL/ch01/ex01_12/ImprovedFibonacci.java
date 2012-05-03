package ch01.ex01_12;

public class ImprovedFibonacci {

    static final int MAX_INDEX = 9;
    static final String TITLE = "フィボナッチ数列";
    
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        String mark = null;
        String[] string = new String[MAX_INDEX]; 
        
        string[0] = "1: " + lo;
    
        for (int i = 2; i <= MAX_INDEX; i++) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            string[i - 1] = i + ": " + hi + mark;
            hi = lo + hi;
            lo = hi - lo;
        }
        
        System.out.println(TITLE);         
        for (int i = 0; i < string.length; i++){
            System.out.println(string[i]);        	
        }
    }
}