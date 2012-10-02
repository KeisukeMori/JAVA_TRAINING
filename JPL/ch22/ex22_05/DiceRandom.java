package ch22.ex22_05;

import java.util.Random;

public class DiceRandom {
	private static final int TIMES = 100000;
	
	public static void main(String [] args){
		new DiceRandom().plotDiceValue(4);
	}
	
	//サイコロインナークラス
	private static class Dice {
		public static final int FACE = 6;
		private Random rnd;
		public Dice(Random rnd){
			this.rnd = rnd;
		}
		public int roll(){
			return rnd.nextInt(FACE) + 1;
		}
	}
	
	public void plotDiceValue(int diceNum){
		Dice[] dices = new Dice[diceNum];
		Random rnd = new Random(System.currentTimeMillis());
		for(int i = 0; i < dices.length; i++){
			dices[i] = new Dice(rnd);
		}
		double sum = 0;
		for(int i = diceNum; i <= diceNum * Dice.FACE; i++){
			double p = simulateDiceRoll(dices, i);
			System.out.println("sum :" + i + " -> " + p);
			sum += p;
		}
		System.out.println("total : " + sum);
	}
	
	private double simulateDiceRoll(Dice[] dices, int sum){
		int time = 0;
		for(int i = 0; i < TIMES; i++){
			int sumValue = 0;
			for(Dice d : dices){
				sumValue += d.roll();
			}
			if(sumValue == sum){
				time++;
			}
		}
		return (double)time / TIMES;
	}
}
