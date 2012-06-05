package ch10.ex10_04;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	//ex1-07 for版
	//    public static void main(String[] args) {
	//        int lo = 1;
	//        int hi = 1;
	//        String mark;
	//        
	//        System.out.println("1: " + lo);
	//        for (int i = MAX_INDEX; 1 < i; i--) {
	//            if (hi % 2 == 0) {
	//                mark = " *";
	//            } else {
	//                mark = "";
	//            }
	//            System.out.println((MAX_INDEX - i + 2)  + ": " + hi + mark);
	//            hi = lo + hi;
	//            lo = hi - lo;
	//        }
	//    }

	//ex1-07 while版
	//	public static void main(String[] args) {
	//		int lo = 1;
	//		int hi = 1;
	//		String mark;
	//		int count = 2;
	//
	//		System.out.println("1: " + lo);
	//		while(hi < 50) {
	//
	//			if (hi % 2 == 0) {
	//				mark = " *";
	//			} else {
	//				mark = "";
	//			}
	//			System.out.println(count  + ": " + hi + mark);
	//			hi = lo + hi;
	//			lo = hi - lo;
	//			count++;
	//		}
	//	}
	
	//ex1-07 do-while版
	//do内がfalseになる可能性がないため特に問題なく記述できる。
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		int count = 2;

		System.out.println("1: " + lo);
		do {
			if (hi % 2 == 0) {
				mark = "*";
			} else {
				mark = "";
			}
				System.out.println(count  + ": " + hi + mark);
				hi = hi + lo;
				lo = hi - lo;
				count++;
			
		} while (hi < 50);
	}
}








