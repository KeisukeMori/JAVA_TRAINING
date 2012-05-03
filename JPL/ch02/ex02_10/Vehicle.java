package ch02.ex02_10;

public class Vehicle {
    public double velocity;  // 現在のスピード
    public double direction;     // 現在の方向
    public String owner;      // 所有者
    
    public long idNum;
    public static long nextID = 0;
    
    // コンストラクタ
    public Vehicle() {
        idNum = nextID++;
    }
    
    // コンストラクタ
    public Vehicle(String firstOwner) {
        this();
        owner = firstOwner;
    }
    
    public String toString() {
        return "ID = " + idNum + "\n" 
            + "スピード = " + velocity + "\n"  
            +"方向 = "+ direction + "\n"
            +"所有者 = "+ owner;
    }
    
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Mike");
        car.velocity = 2.0;
        car.direction = Math.PI / 2;        
        System.out.println(car);

        
        Vehicle train = new Vehicle("Joe");
        train.velocity = 4.0;
        train.direction = Math.PI * 2;
        System.out.println(train);
        
        Vehicle airplane = new Vehicle("Nick");
        airplane.velocity = 8.0;
        airplane.direction = Math.PI;
        System.out.println(airplane);
    }
}
