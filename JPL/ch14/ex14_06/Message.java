package ch14.ex14_06;

public class Message implements Runnable{
	private int interval = 0;
	
	public Message(int interval) {
		this.interval = interval;
		new Thread(this).start();
	}
	
	public synchronized void notifyTime() {
		notifyAll();
	}
	
	public synchronized void showMessage() throws InterruptedException{
		while (Time.getTimeCount() == 0 || Time.getTimeCount() % interval != 0) {
			wait();
		}
		System.out.println(interval + " sec");
	}

	@Override
	public void run() {
		try {
			for (;;) {
				showMessage();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException occured");
			return;
		}
	}
}
