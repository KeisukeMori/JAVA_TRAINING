package ch14.ex14_09;

public class ThreadRunnable implements Runnable{
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
