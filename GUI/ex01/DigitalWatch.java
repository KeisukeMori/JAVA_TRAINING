package ex01;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DigitalWatch extends Frame {
	private int hour;           //時を入れる変数を宣言
	private int minute;           //分を入れる変数を宣言
	private int second;           //秒を入れる変数を宣言


	boolean a = true;       
	Thread th;


	public DigitalWatch() {
		setTitle("　デ　ジ　タ　ル　時　計　");
		//フレーム作成
		setSize(300, 80);
		setResizable(false);
		setVisible(true);
		setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));

		th = new Thread();

		addWindowListener(new CurrentWindowAdapter());

	}

/*
	public void run() {
		try{
			th.sleep(1000);  //スリープ１秒
		} catch (InterruptedException e) {
			e.getStackTrace();
		}               
		repaint();

	}
*/
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY); //時を代入
		minute = calendar.get(Calendar.MINUTE);      //分を代入
		second= calendar.get(Calendar.SECOND);       //秒を代入
		return padZero(hour) + ":" + padZero(minute) + ":" + padZero(second);
	}

	public void paint(Graphics g)
	{
		g.drawString(getTime(), 50, 59);
		try{
			th.sleep(1000);  //スリープ１秒
		} catch (InterruptedException e) {
			e.getStackTrace();
		}               
		repaint();
	}
	public String padZero(int val) {
		if(val < 10){
			return "0" + val;
		} else {
			return "" + val;
		}
	}
		
	
	public static void main(String args[]) {
		new DigitalWatch();

		//th.start();   //スレッドスタート
	}
}


class CurrentWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {   //×を押されたときの処理
		System.exit(0);
	}
}
