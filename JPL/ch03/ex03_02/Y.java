package ch03.ex03_02;

public class Y extends X{
	protected int yMask = 0xff00;
	{
		traceStep();	//Yのフィールドの初期化後
	}
	
	public Y(){
		fullMask |= yMask;
		traceStep();	//Yのコンストラクタの実行後
	}
	
	public void traceStep(){
		System.out.printf("%-2d 0x%04x 0x%04x 0x%04x \n",
				super.getStep(), super.getXMask(),yMask, super.getFullMask());

		super.addStep();
	}
	
	public int getYMask(){
		return yMask;
	}
	
	public static void main(String[] args) {
		System.out.printf("%2s %-6s %-6s %-6s \n", "no","xMask","yMask","fullMask");
		Y y = new Y();
		}
	}