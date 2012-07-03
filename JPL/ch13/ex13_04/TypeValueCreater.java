package ch13.ex13_04;

import java.util.ArrayList;
import java.util.List;


public class TypeValueCreater {
    
    public static ArrayList parse(String str) {
        if(str == null) {
            throw new NullPointerException();
        }
        
        ArrayList list = new ArrayList();       
        String[] line = str.split("\n");
        
        for (String pair : line) {            
            String type = pair.split(" ")[0];
            String value = pair.split(" ")[1];
			if(type.equals("Boolean")) {
				list.add(Boolean.parseBoolean(value));
			} else if(type.equals("Byte")) {
				list.add(Byte.parseByte(value));
			} else if(type.equals("Charactor")) {
				list.add(value.charAt(0));
			} else if(type.equals("Short")) {
				list.add(Short.parseShort(value));
			} else if(type.equals("Integer")) {
				list.add(Integer.parseInt(value));
			} else if(type.equals("Long")) {
				list.add(Long.parseLong(value));
			} else if(type.equals("Float")) {
				list.add(Float.parseFloat(value));
			} else if(type.equals("Double")) {
				list.add(Double.parseDouble(value));
			} else {
				}
			}
		return list;
    }
}

