package ch13.ex13_05;

public class QuoInsert {
    private final static int DIGITS = 3;
    
    static String quoInsert(String number) {
        
        StringBuilder buf = new StringBuilder(number);
        
        //3桁ごとにループをまわす
        for (int point = buf.length() - DIGITS; point > 0; point-= DIGITS) {
        	//","の挿入
            buf.insert(point,",");
        }
        return buf.toString();
    }
}

