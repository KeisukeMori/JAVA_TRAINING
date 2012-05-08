package ch04.ex04_01;

public class Battery implements EnergySource {
	private double battery;

	Battery(double battery) {
		this.battery = battery; 
	}
	
	public boolean empty(){

		if(battery > 0) {
			return false;
		} else {
			return true;
		}
	}

}
