package ch03.ex03_08;

public class PassengerVehicle extends Vehicle {
	private int seatCapacity;    // 座席数
	private int ridingPerson;       // 座っている人の数

	public void setSeatCapacity(int value) {
		seatCapacity = value;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setRidingPerson(int value) {
		ridingPerson = value;
	}

	public int getRidingPerson() {
		return ridingPerson; 
	}

	public PassengerVehicle(String firstOwner) {
		super(firstOwner);
	} 

	public String toString() {
		return super.toString() + "\n" 
		+ "座席数 = " + seatCapacity + "\n"  
		+ "座っている人の数 = "+ ridingPerson;

	}

	public static void main(String[] args) {
		PassengerVehicle car = new PassengerVehicle("Mike");
		car.setVelocity(2);
		car.setSeatCapacity(1);
		car.setRidingPerson(1);
		System.out.println(car + "\n");


		PassengerVehicle copy = (PassengerVehicle) car.clone();
		System.out.println(copy + "\n");
	}
}
