package ch03.ex03_01;

public class PassengerVehicle extends Vehicle {
	private int seatCapacity;    // 座席数
	private int ridingPerson;       // 座っている人の数

	public void setSeatCapacity(int value) {
		this.seatCapacity = value;
	}
	
	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setRidingPerson(int value) {
		this.ridingPerson = value;
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
		+ "座っている人数 = "+ ridingPerson;

	}

	public static void main(String[] args) {
		PassengerVehicle car = new PassengerVehicle("Mike");
		car.setVelocity(2.0);
		car.setDirection(Math.PI / 2);
		car.setSeatCapacity(1);
		car.setRidingPerson(1);
		System.out.println(car + "\n");

		PassengerVehicle train = new PassengerVehicle("Joe");
		train.setVelocity(4.0);
		train.setDirection(Math.PI);
		train.setSeatCapacity(1);
		train.setRidingPerson(1);
		System.out.println(train + "\n");

		PassengerVehicle airplane = new PassengerVehicle("Nick");
		airplane.setVelocity(8.0);
		airplane.setDirection(Math.PI * 2);
		airplane.setSeatCapacity(4);
		airplane.setRidingPerson(2);
		System.out.println(airplane);
	}
}