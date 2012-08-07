package ch14.ex14_03;

public class Main {
	private int value = 0;
	public synchronized void add(int val) {
		value += val;
		System.out.println("Val: " + value + " Thread name: " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		Main obj = new Main();
		Runnable hoge = new Test(obj);
		
		new Thread(hoge).start();
	}
}
