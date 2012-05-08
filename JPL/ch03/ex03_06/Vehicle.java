package ch03.ex03_06;

public class Vehicle {
	public final static int TURN_LEFT = 0; 
	public final static int TURN_RIGHT = 1; 

	private double velocity;	// 現在のスピード
	private double direction;	// 現在の方向
	private String owner;		// 所有者

	private long idNum;
	private static long nextID = 0;
	private EnergySource es; 

	public Vehicle() {
		idNum = nextID++;
	}

	public Vehicle(String firstOwner) {
		this();
		owner = firstOwner;
	}

	public void setVelocity(double value) {
		velocity = value;
	}
	public double getVelocity() {
		return velocity;
	}

	public void setDirection(double value) {
		direction = value;
	}
	public double getDirection() {
		return direction; 
	}

	public String toString() {
		return "ID = " + idNum + "\n" 
		+ "スピード = " + velocity + "\n"  
		+"方向 = "+ direction + "\n"
		+"所有者 = "+ owner;
	}

	public void changeSpeed(double value) {
		setVelocity(value);
	}

	public void stop() {
		setVelocity(0);
	}

	public void turn(double turnValue) {
		direction += turnValue;
	}

	public void turn(int turn) {
		switch (turn) {
		case TURN_LEFT: turn(-Math.PI / 2);
		break;
		case TURN_RIGHT: turn(Math.PI / 2);
		break;
		}
	}
	Vehicle(EnergySource es){
		this();
		this.es = es;
	}
	
	public void start(){
		if(es.empty())
			System.out.println("動力源がたりません。");
		else
			System.out.println("発車できます。");
	}
	
	public static void main(String args[]){
		Vehicle gasVehicle = new Vehicle(new GasTank(10.0));
		Vehicle noGasVehicle = new Vehicle(new GasTank(0.0));
		Vehicle batteryVehicle = new Vehicle(new Battery(100.0));
		Vehicle noBatteryVehicle = new Vehicle(new Battery(0.0));
		
		gasVehicle.start();
		noGasVehicle.start();
		batteryVehicle.start();
		noBatteryVehicle.start();
	}

}
