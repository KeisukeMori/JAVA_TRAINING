package ch14.ex14_05;


public class Test2 implements Runnable {
	
	public void run() {
		try {
			for (;;) {
				Main.subtractValue(8);
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			return;
		}
	}
}
