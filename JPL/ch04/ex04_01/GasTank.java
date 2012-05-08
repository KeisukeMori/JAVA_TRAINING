package ch04.ex04_01;

public class GasTank implements EnergySource{
	private double fuel;

	public GasTank(double fuel){
		this.fuel = fuel;
	}
	public boolean empty(){
		if(fuel > 0){
			return false;
		} else {
			return true;	
		}
	}
}
