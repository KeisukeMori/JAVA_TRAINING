package ch14.ex14_04;

public class Main {
	private static int value = 0;
	public synchronized static void add(int val) {
		value += val;
		System.out.println("Val: " + value + " Thread name: " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		Runnable hoge = new Test();

		new Thread(hoge).start();
	}
}
