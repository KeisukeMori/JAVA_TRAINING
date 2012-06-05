package ch07.ex07_02;

public class Data {
	
	public static boolean mBoolean = true;
	public static char Char = 'a';
	public static byte maxByte = Byte.MAX_VALUE;
	public static byte minByte = Byte.MIN_VALUE;
	public static short maxShort = Short.MAX_VALUE;
	public static short minShort = Short.MIN_VALUE;
	public static int maxInt = Integer.MAX_VALUE;
	public static int minInt = Integer.MIN_VALUE;
	public static long maxLong = Long.MAX_VALUE;
	public static long minLong = Long.MIN_VALUE;
	public static float maxFloat = Float.MAX_VALUE;
	public static float minFloat = Float.MIN_VALUE;
	public static double maxDouble = Double.MAX_VALUE;
	public static double minDouble = Double.MIN_VALUE;

	public static void main(String[] args) {
		System.out.println("maxByte: " + maxByte);
		System.out.println("minByte: " + minByte);
		System.out.println("maxShort: " + maxShort);
		System.out.println("minShort: " + minShort);
		System.out.println("maxInt: " + maxInt);
		System.out.println("minInt: " + minInt);
		System.out.println("maxLong: " + maxLong);
		System.out.println("minlong: " + minLong);
		System.out.println("maxFloat: " + maxFloat);
		System.out.println("minFloat: " + minFloat);
		System.out.println("maxDouble: " + maxDouble);
		System.out.println("minDouble: " + minDouble);
		
		int testInt = 0;
		System.out.println("(int)maxByte: " + (testInt = (int)maxByte));
		System.out.println("(int)minByte: " + (testInt = (int)minByte));
		System.out.println("(int)maxShort: " + (testInt = (int)maxShort));
		System.out.println("(int)minShort: " + (testInt = (int)minShort));
		System.out.println("(int)maxInt: " + (testInt = (int)maxInt));
		System.out.println("(int)minInt: " + (testInt = (int)minInt));
		System.out.println("(int)maxLong: " + (testInt = (int)maxLong));
		System.out.println("(int)minlong: " + (testInt = (int)minLong));
		System.out.println("(int)maxFloat: " + (testInt = (int)maxFloat));
		System.out.println("(int)minFloat: " + (testInt = (int)minFloat));
		System.out.println("(int)maxDouble: " + (testInt = (int)maxDouble));
		System.out.println("(int)minDouble: " + (testInt = (int)minDouble));

		long testLong = 0;
		System.out.println("(long)maxByte: " + (testLong = (long)maxByte));
		System.out.println("(long)minByte: " + (testLong = (long)minByte));
		System.out.println("(long)maxShort: " + (testLong = (long)maxShort));
		System.out.println("(long)minShort: " + (testLong = (long)minShort));
		System.out.println("(long)maxInt: " + (testLong = (long)maxInt));
		System.out.println("(long)minInt: " + (testLong = (long)minInt));
		System.out.println("(long)maxLong: " + (testLong = (long)maxLong));
		System.out.println("(long)minlong: " + (testLong = (long)minLong));
		System.out.println("(long)maxFloat: " + (testLong = (long)maxFloat));
		System.out.println("(long)minFloat: " + (testLong = (long)minFloat));
		System.out.println("(long)maxDouble: " + (testLong = (long)maxDouble));
		System.out.println("(long)minDouble: " + (testLong = (long)minDouble));
		
		double testDouble = 0;
		System.out.println("(double)maxByte: " + (testDouble = (double)maxByte));
		System.out.println("(double)minByte: " + (testDouble = (double)minByte));
		System.out.println("(double)maxShort: " + (testDouble = (double)maxShort));
		System.out.println("(double)minShort: " + (testDouble = (double)minShort));
		System.out.println("(double)maxInt: " + (testDouble = (double)maxInt));
		System.out.println("(double)minInt: " + (testDouble = (double)minInt));
		System.out.println("(double)maxLong: " + (testDouble = (double)maxLong));
		System.out.println("(double)minlong: " + (testDouble = (double)minLong));
		System.out.println("(double)maxFloat: " + (testDouble = (double)maxFloat));
		System.out.println("(double)minFloat: " + (testDouble = (double)minFloat));
		System.out.println("(double)maxDouble: " + (testDouble = (double)maxDouble));
		System.out.println("(double)minDouble: " + (testDouble = (double)minDouble));

	}
}



