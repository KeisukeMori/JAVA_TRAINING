package ex01;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DigitalWatch extends Frame {
	private int hour;           //��������ϐ���錾
	private int minute;           //��������ϐ���錾
	private int second;           //�b������ϐ���錾


	boolean a = true;       
	Thread th;


	public DigitalWatch() {
		setTitle("�@�f�@�W�@�^�@���@���@�v�@");
		//�t���[���쐬
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
			th.sleep(1000);  //�X���[�v�P�b
		} catch (InterruptedException e) {
			e.getStackTrace();
		}               
		repaint();

	}
*/
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY); //������
		minute = calendar.get(Calendar.MINUTE);      //������
		second= calendar.get(Calendar.SECOND);       //�b����
		return padZero(hour) + ":" + padZero(minute) + ":" + padZero(second);
	}

	public void paint(Graphics g)
	{
		g.drawString(getTime(), 50, 59);
		try{
			th.sleep(1000);  //�X���[�v�P�b
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

		//th.start();   //�X���b�h�X�^�[�g
	}
}


class CurrentWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {   //�~�������ꂽ�Ƃ��̏���
		System.exit(0);
	}
}
