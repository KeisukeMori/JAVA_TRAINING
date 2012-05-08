package ch03.ex03_06;

public class GasTank extends EnergySource{
	private double Gas;

	public GasTank(double Gas){
		this.Gas = Gas;
	}
	public boolean empty(){
		System.out.println("ガスタンクの容量をチェックします。");
		if(Gas > 0){
			return false;
		} else {
			return true;	
		}
	}
}
