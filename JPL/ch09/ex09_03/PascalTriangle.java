package ch09.ex09_03;

public class PascalTriangle {

	private int[][] Triangle;
//	private static int DEPTH;	

	public PascalTriangle(int depth){
		Triangle = new int[depth][depth];
		for(int i = 0; i < Triangle.length; i++){
			for(int j = 0; j <= i; j++){
	            if(j == 0){
	            	Triangle[i][j] = 1;
	            } else {
	            	Triangle[i][j] = Triangle[i - 1][j - 1] + Triangle[i - 1][j];
	            }
			}
		}
	}

	public void show(){
		for(int i = 0; i < Triangle.length; i++){
			for(int j = 0; j <= i; j++){
				System.out.print(Triangle[i][j]);
				if(j != i){
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		PascalTriangle pt = new PascalTriangle(12);
		pt.show();
	}
}