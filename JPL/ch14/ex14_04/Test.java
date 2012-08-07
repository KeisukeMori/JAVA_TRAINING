package ch14.ex14_04;

public class Test implements Runnable {
	
	public void run() {
		try {
			for (;;) {
				Main.add(5);
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			return;
		}
	}
}
