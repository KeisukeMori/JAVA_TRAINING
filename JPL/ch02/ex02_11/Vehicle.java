package ch02.ex02_11;

public class Vehicle {
	public double velocity;  // 現在のスピード
	public double direction;     // 現在の方向
	public String owner;      // 所有者
	public long idNum;
	public static long nextID = 0;

	public Vehicle() {
		idNum = nextID++;
	}

	public Vehicle(String firstOwner) {
		this();
		owner = firstOwner;
	}
    public String toString() {
        return "ID = " + idNum + "\n" 
            + "スピード = " + velocity + "\n"  
            +"方向 = "+ direction + "\n"
            +"所有者 = "+ owner + "\n";
    }

}
