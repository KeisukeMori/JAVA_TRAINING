package ch17.ex17_01;

public class CheckAvailableMemory {

	public static void main(String[] args) {
		System.out.println("freeMemory:" + checkAvailableMemory() + "Byte");
		
		System.out.println("Create 10000 Double Object.");
		Double[] doubleArr = new Double[10000];
		for(int i = 0; i < 10000; i++){
			doubleArr[i] = new Double(i);
		}
		System.out.println("freeMemory:" + checkAvailableMemory() + "Byte");
		
		System.out.println("GC.");
		doubleArr = null;
		Runtime.getRuntime().gc();
		System.out.println("freeMemory:" + checkAvailableMemory() + "Byte");

	}
	
	
	public static long checkAvailableMemory(){
		return Runtime.getRuntime().freeMemory();
	}

}
