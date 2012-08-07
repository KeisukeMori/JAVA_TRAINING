package ch14.ex14_05;

public class Main {
	private static int value = 0;
	public static void addValue(int val) {
		synchronized (Main.class) {
			value += val;
			System.out.println("Value: " + value + " Thread name: " + Thread.currentThread().getName());
		}
	}
	
	public static void subtractValue(int val) {
		synchronized (Main.class) {
			value -= val;
			System.out.println("Value: " + value + " Thread name: " + Thread.currentThread().getName());
		}	
	}
	
	public static void main(String[] args) {
		Runnable test1 = new Test1();
		Runnable test2 = new Test2();
		
		new Thread(test1).start();
		new Thread(test2).start();
	}
}
