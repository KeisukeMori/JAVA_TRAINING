package ch14.ex14_06;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
	private static int timeCount = 0;
	private Timer timer = null;
	private TimeTask timeTask = null;
	private List<Message> messageList = new LinkedList<Message>();
	
	public void start() {
		timer = new Timer(true);
		timeTask = new TimeTask();
		// 1秒ごと
		timer.schedule(timeTask, 0, 1000);
	}
	
	private synchronized void update() {
		timeCount ++;
		System.out.println("Total time is " + timeCount + " sec");
		
		for (Message message: messageList) {
			message.notifyTime();
		}
	}
	
	public void registerMessage(Message message) {
		messageList.add(message);
	}
	
	public static int getTimeCount() {
		return timeCount;
	}
	
	class TimeTask extends TimerTask {
	    public void run() {
	        update();
	    }
	}
	
	public static void main(String[] args) {
		Time time = new Time();
		time.registerMessage(new Message(15));
		time.registerMessage(new Message(7));
		time.start();
	}
}
