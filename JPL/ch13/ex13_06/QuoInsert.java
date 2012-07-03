package ch13.ex13_06;

public class QuoInsert {
    
    static String quoInsert(String number, int quo, String separator) {
        
        StringBuilder buf = new StringBuilder(number);
        
        //指定された桁ごとにループをまわす
        for (int point = buf.length() - quo; point > 0; point-= quo) {
        	//セパレーターの挿入
            buf.insert(point, separator);
        }
        return buf.toString();
    }
}

