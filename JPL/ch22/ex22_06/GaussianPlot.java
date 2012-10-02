package ch22.ex22_06;

import java.util.Random;

public class GaussianPlot {

	private static final int COUNT = 300000;
	private static final int COLUMN = 60;
	private static final int ROW = 40;
	private static final double MAX_POSITIVE_NUM = 3.0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GaussianPlot.plot();

	}

	public static void plot(){
		Random rnd = new Random(System.currentTimeMillis());
		
		double[] rowBase = new double[ROW];
		int [] count = new int[ROW];
		
		rowBase[0] = -1 * MAX_POSITIVE_NUM;
		for(int i = 1; i < ROW; i++){
			rowBase[i] = rowBase[i-1] + (MAX_POSITIVE_NUM*2) / ROW;
		}
		
		for(int i = 0; i < COUNT ; i++){
			double val = rnd.nextGaussian();
			for(int j = 1; j < ROW - 1; j++){
				if(rowBase[j] < val && val <= rowBase[j+1] ){
					count[j]++;
				}
			}
		}
		
		int maxCount = -1;
		for(int i = 0; i < ROW; i++){
			if(count[i] > maxCount){
				maxCount = count[i];
			}
		}
		double rate = 1.0;
		if(maxCount > COLUMN){
			rate = maxCount / COLUMN;
		}
		for(int i = 0; i < ROW; i++){
			count[i] /= rate;
		}
		
		for(int i = 0; i < ROW; i++){
			StringBuilder str = new StringBuilder();
			for(int j = 0; j < count[i]; j++){
				str.append("□");
			}
			System.out.println(str);
		}
	}
	
	
}
