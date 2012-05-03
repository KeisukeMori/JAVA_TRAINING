package ch02.ex02_06;

public class LinkedList {
    public Object object;
    public LinkedList next;
    public Vehicle vehicle;
    
    public void show() {
        System.out.println("object = " + object);
        System.out.println("vehicle owner= " + vehicle.owner);
        System.out.println("vehicle idnum= " + vehicle.idNum);
        System.out.println("");
        
        if (next != null)
            next.show();
    }
    
    public static void main(String[] args) {
        
        Vehicle car = new Vehicle();
        car.idNum = 0;
        car.owner = "Mike";
        car.velocity = 2.0;
        
        Vehicle train = new Vehicle();
        train.idNum = 1;
        train.owner = "Joe";
        train.velocity = 4.0;
        
        Vehicle airPlane = new Vehicle();
        airPlane.idNum = 2;
        airPlane.owner = "Nick";
        airPlane.velocity = 8.0;
        
    	LinkedList item1 = new LinkedList();
        LinkedList item2 = new LinkedList();
        LinkedList item3 = new LinkedList();
        
        item1.object = 1234;
        item1.next = item2;
        item1.vehicle = car;

        item2.object = 3456;  
        item2.next = item3;
        item2.vehicle = train;
        
        item3.object = 7890;
        item3.next = null;
        item3.vehicle = airPlane;

        
        item1.show();
    }
}


