package ch16.ex16_11;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Game extends JFrame{
	/**
	 * 途中までしかできていません。クラスロードに失敗します。
	 */
	private static final long serialVersionUID = 1L;
	private static String[] nameArr;
	private static int it = 0;
	private int count = 0;
	public static void main(String[] args){

		nameArr = args;
		String name;
		while ((name = getNextPlayer() ) != null){
			try{
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			}
			catch(Exception e){
				reportException(e);
			}
		}
	}
	
	private JPanel panel;
	private JLabel[] label = new JLabel[9];
	private JLabel msgLabel;
	//private int[] states = new int[9];
	
	public Game(){
		super();
		setLayout(new GridLayout(2, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 300, 200);
		setTitle("Game");
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		
		msgLabel = new JLabel();
		
		for(int i = 0; i < 9; i++){
			label[i] = new JLabel();
			label[i].setText("-");
			panel.add(label[i]);
			//states[i] = 0;
		}
		
		panel.setVisible(true);
		this.getContentPane().add(panel);
		this.getContentPane().add(msgLabel);
		
		setVisible(true);
	}
	
	public boolean setState(int x, int y, String s){
		int num = 3 * x + y;
//		if(states[num] != 0){
//			return false;
//		}
		if(label[num].getText() != "-"){
			return false;
		}
		
		else{
			//states[num] = s;
			String text = null;
			if (s == "○") {
				text = "○";
			} else {
				text = "×";
			}
//			label[num].setText(String.valueOf(s));
			label[num].setText(text);
			try{
				//一瞬で表示が終了すると分かりづらいので、一時停止する。
				Thread.sleep(6000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return true;
		}
	}
//	public int getState(int x, int y){
//		if(x > 2 || y > 2){
//			return -1;
//		}
//		return states[3 * x + y];
//	}
	
	public String getState(int x, int y){
		if(x > 2 || y > 2){
			return "error";
		}
		return label[3 * x + y].getText();
	}
	
	public int reportScore(String name){
		int score = 0;
		//縦
		for(int i = 0; i < 3; i++){
			if(getState(i, 0) == getState(i, 1) && getState(i, 1) == getState(i, 2) && getState(i, 0) == "○"){
				score++;
			}
		}
		//横
		for(int i = 0; i < 3; i++){
			if(getState(0, i) == getState(1, i) && getState(1, i) == getState(2, i) && getState(0, i) == "○"){
				score++;
			}
		}
		//斜め
		if(getState(0, 0) == getState(1, 1) && getState(1, 1) == getState(2, 2) && getState(0, 0) == "○"){
			score++;
		}
		if(getState(0, 2) == getState(1, 1) && getState(1, 1) == getState(2, 0) && getState(1, 1) == "○"){
			score++;
		}
		msgLabel.setText(name + ": score = " + score);
		return score;
	}
	
	public static String getNextPlayer(){
		if(it >= nameArr.length){
			return null;
		}
		else {
			it++;
			return nameArr[it - 1];
		}
	}
	
	public static void reportException(Exception e){
		e.printStackTrace();
	}
	
	public void play(){
		if(count > 8){
			return;
		}
		int x;
		int y;
		do {
			Random rnd = new Random();
			x = rnd.nextInt(3);
			y = rnd.nextInt(3);
		} while(!setState(x, y, "×"));
		msgLabel.setText("game : " + "[" + x + "," + y + "]" + ":" + getCount());
		setCount();
	}
	
	public int setCount(){
		count++;
		return count;
	}
	public int getCount(){
		return count;
	}
	public void printMessage(String str){
		msgLabel.setText(str);
	}
}
