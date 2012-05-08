package ch03.ex03_06;

public class Battery extends EnergySource {
	private double battery;

	Battery(double battery) {
		this.battery = battery; 
	}
	
	public boolean empty(){
		System.out.println("バッテリーの容量をチェックします。");
		if(battery > 0) {
			return false;
		} else {
			return true;
		}
	}

}
