package ch02.ex02_07;

public class Vehicle {
    public double velocity;  // 現在のスピード
    public double direction;     // 現在の方向
    public String owner;    // 所有者
    
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
    
    public void show(String Vehicle) {
        System.out.println(Vehicle);
        System.out.println("  ID = " + idNum);
        System.out.println("  スピード = " + velocity);
        System.out.println("  方向 = "+ direction);
        System.out.println("  所有者 = "+ owner);
    }
    
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Mike");
        car.velocity = 2.0;
        car.direction = Math.PI / 2;
        car.show("car");
        
        Vehicle train = new Vehicle("Joe");
        train.velocity = 4.0;
        train.direction = Math.PI * 2;
        train.show("train");
        
        Vehicle airplane = new Vehicle("Nick");
        airplane.velocity = 8.0;
        airplane.direction = Math.PI ;
        airplane.show("airplane");
    }
}
