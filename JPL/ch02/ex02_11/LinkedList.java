package ch02.ex02_11;

public class LinkedList {
    public Object object;
    public LinkedList next;

    // コンストラクタ
	public LinkedList(Object obj, LinkedList nextNode) {
		this.object = obj;
		this.next = nextNode;
	}

    // コンストラクタ
	public LinkedList(Object Obj) {
		this(Obj, null);
	}
    
    public void show() {
        System.out.println(this);
		if (object instanceof Vehicle) {
			Vehicle tmpVehicle = (Vehicle) object;
			tmpVehicle.toString();
		}
		if(next!=null)
			next.show();
	}
  
	public String toString(){
		if (object instanceof Vehicle) {
			String infoVihicle;
			Vehicle tmpVehicle = (Vehicle) object;
			infoVihicle = tmpVehicle.toString();
			
			if(next != null)
				infoVihicle += next.toString();
			return infoVihicle;
		}
		return null;
	}

    
    public static void main(String[] args) {
    	
		Vehicle car = new Vehicle("Mike");
		car.velocity = 2.0;
		car.direction = Math.PI / 2;
		
		Vehicle train = new Vehicle("Joe");
		train.velocity = 4.0;
		train.direction = Math.PI * 2;
		
		Vehicle airplane = new Vehicle("Nick");
		airplane.velocity = 8.0;
		airplane.direction = Math.PI;

        LinkedList item3 = new LinkedList(airplane, null);
        LinkedList item2 = new LinkedList(train, item3);
        LinkedList item1 = new LinkedList(car, item2);
        
		System.out.println(item1);
    }
}
