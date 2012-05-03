package ch02.ex02_14;

public class Vehicle {
    private double velocity;  // 現在のスピード
    private double direction;     // 現在の方向
    private String owner;      // 所有者
    
    private long idNum;
    private static long nextID = 0;
    
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
            +"所有者 = "+ owner + "\n";
    }
}