package ch03.ex03_10;

public class LinkedList implements Cloneable {
    private Object object;
    LinkedList next;

	//コンストラクタ
	public LinkedList(Object object, LinkedList next) {
		this.object = object;
		this.next = next;
	}
	
	//コンストラクタ
	public LinkedList(Object object) {
		this(object, null);
	}
    
	public void show() {
		System.out.println(this);
		if (object instanceof Vehicle) {
			Vehicle tmpVehicle = (Vehicle) object;
			tmpVehicle.toString();
		}
		if(next != null)
			next.show();
	}
	
	public String toString(){
		if (object instanceof Vehicle) {
			String tmpv;
			Vehicle tmpVehicle = (Vehicle) object;
			tmpv = tmpVehicle.toString();
			if(next != null)
				tmpv += next.toString();
			return tmpv;
		}
		return null;
	}
  
	public Object getObj() {
		return object;
		}
	public void setObj(Object object) {
		this.object = object;
		}
	
	public LinkedList getNextNode() {
		return next;
		}
	public void setNextNode(LinkedList nextNode) {
		this.next = nextNode;
		}
	
	public int countList(){
		if(next == null)
			return 1;
		else
			return 1 + next.countList();
	}

	
    public Object clone() {
        try {
            LinkedList list = (LinkedList) super.clone();
            if (next != null) {
                list.next = (LinkedList) next.clone(); 
            }
            return list; 
            
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }   
    public static void main(String[] args) {

		Vehicle car = new Vehicle("Mike");
		car.setVelocity(2.0);
		car.setDirection(Math.PI / 2);

		Vehicle train = new Vehicle("Joe");
		train.setVelocity(4.0);
		train.setDirection(Math.PI * 2);

		Vehicle airplane = new Vehicle("Nick");
		airplane.setVelocity(8.0);
		airplane.setDirection(Math.PI);
    	
		LinkedList item3 = new LinkedList(airplane, null);
		LinkedList item2 = new LinkedList(train, item3);
		LinkedList item1 = new LinkedList(car, item2);

		System.out.println("LinkedList");
        item1.show();
		
        // 複製を作成        
        LinkedList cloneList = (LinkedList) item1.clone();
        
        // 速度を変更してみる
        Vehicle tmpVehicle = (Vehicle)cloneList.getNextNode().getObj();
		tmpVehicle.setVelocity(100);
		System.out.println("Velocity change");
		cloneList.show();
        
        // リストの順番を入れ替えてみる
        item1.next = item3;
        item3.next = item2;
        item2.next = null;
        
		System.out.println("Node change");
        item1.show();
        System.out.println("\n");
		System.out.println("Node change Clone");        
        cloneList.show();

    }
}