package ch02.ex02_09;

public class Vehicle {
    public double velocity;  // 現在のスピード
    public double direction;     // 現在の方向
    public String owner;     // 所有者
    
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
    
    public static long maxID() {
        return nextID - 1;
    }
    
    public void show(String kind) {
        System.out.println(kind);
        System.out.println("  ID = " + idNum);
        System.out.println("  スピード = " + velocity);
        System.out.println("  方向 = "+ direction);
        System.out.println("  所有者 = "+ owner);
    }
    
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Mike");
        Vehicle train = new Vehicle("Joe");
        Vehicle airplane = new Vehicle("Nick");
        
        System.out.println("maxID = " + Vehicle.maxID());
    }
}