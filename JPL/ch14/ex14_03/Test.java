package ch14.ex14_03;

public class Test implements Runnable {
	private Main obj;
	Test(Main obj) {
		this.obj = obj;
	}
	
	
	public void run() {
		try {
			for (;;) {
				obj.add(5);
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			return;
		}
	}
}
