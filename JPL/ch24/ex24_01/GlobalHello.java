package ch24.ex24_01;

import java.util.ResourceBundle;

/**
 * 
 * @author kmori
 *
 */
public class GlobalHello {
	public static void main(String[] args){
		ResourceBundle res = ResourceBundle.getBundle("GlobalRes");
		String msg;
		if(args.length > 0){
			msg = res.getString(GlobalRes.GOODBYE);
		}
		else{
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);
	}

}
