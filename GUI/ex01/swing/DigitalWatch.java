package ex01.swing;

//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class DigitalWatch extends JFrame {
	private int hour;          
	private int minute;           
	private int second;         
	Container con;

	boolean a = true;       
	Thread th;


	public DigitalWatch() {
		setTitle("デジタル時計");
		setSize(300, 80);
		con = getContentPane();
		setResizable(false);
		setVisible(true);
		WPanel wp = new WPanel();
		con.add(wp,"Center");
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		th = new Thread();

		Timer timer = new Timer(true);
		timer.schedule(new drawTimer(), 0, 1000);
	}
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY); 
		minute = calendar.get(Calendar.MINUTE);      
		second= calendar.get(Calendar.SECOND);       
		return padZero(hour) + ":" + padZero(minute) + ":" + padZero(second);
	}
	public class  WPanel extends JPanel { 
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			g.setFont ( new Font("timeRoman",Font.PLAIN,40));
			g.drawString(getTime(), 50, 40);
			setBackground(Color.white);
			//			try{
			//				th.sleep(1000);
			//			} catch (InterruptedException e) {
			//				e.getStackTrace();
			//			}
			//			repaint();
		}
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

	}

	private class drawTimer extends TimerTask {
		public void run() {
			repaint();
		}  
	}
}



//class CurrentWindowAdapter extends WindowAdapter {
//	public void windowClosing(WindowEvent e) {
//		System.exit(0);
//	}
//}
