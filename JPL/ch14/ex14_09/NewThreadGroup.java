package ch14.ex14_09;

public class NewThreadGroup{
	
	private void Show(ThreadGroup group) {
		Thread[] list = new Thread[group.activeCount()];
		int threadCount = group.enumerate(list, true);
		
		System.out.println("Start");
		for (Thread thread: list) {
			System.out.println("Thread: " + thread.getName() + ", Group: " + thread.getThreadGroup().getName());
		}
		System.out.println("End");
	}
	
	public void ShowThread(final ThreadGroup group) {
		new Thread(new Runnable() {
			public void run() {
				for (;;) { 
					Show(group);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
			} 
		}, "Thread").start();
	}


	
	public static void main(String[] args) {
		NewThreadGroup obj = new NewThreadGroup();
		ThreadGroup group1 = new ThreadGroup("Group1");
		ThreadGroup group2 = new ThreadGroup(group1, "Group2");
		Thread th1 = new Thread(group1, new ThreadRunnable(), "Thread1");
		Thread th2 = new Thread(group1, new ThreadRunnable(), "Thread2");
		Thread th3 = new Thread(group1, new ThreadRunnable(), "Thread3");
		th1.start();
		th2.start();
		th3.start();
		
		obj.ShowThread(group1);
	}
}
