package ch02.ex02_15;

public class Vehicle {
    private double velocity;	// 現在のスピード
    private double direction;   // 現在の方向
    private String owner;      	// 所有者
    
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
            +"所有者 = "+ owner;
    }
    
    public void changeSpeed(double value) {
        setVelocity(value);
    }
    
    public void stop() {
        setVelocity(0);
    }
    
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Mike");
        car.setVelocity(2.0);
        car.setDirection(Math.PI / 2);
        
        car.changeSpeed(4.0);
        System.out.println(car);
        
        System.out.println();
        
        car.stop();
        System.out.println(car);
        System.out.println();
        
        Vehicle train = new Vehicle("Joe");
        train.setVelocity(4.0);
        train.setDirection(Math.PI * 2);
        
        train.changeSpeed(8.0);
        System.out.println(train);
        
        System.out.println();
        
        train.stop();
        System.out.println(train);
        System.out.println();
        
        Vehicle airplane = new Vehicle("Nick");
        airplane.setVelocity(8.0);
        airplane.setDirection(Math.PI);
        
        airplane.changeSpeed(16.0);
        System.out.println(airplane);
        
        System.out.println();
        
        airplane.stop();
        System.out.println(airplane);
    }
}
