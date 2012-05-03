package ch02.ex02_16;

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
		if(next != null)
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
	
	public Object getObject(){
		return object;
	}
	
	/*オブジェクトの変更は許可するべきでない */
	public void setObject(Object object){
		this.object = object;
	}
	
	public LinkedList getNext(){
		return next;
	}
	
	public void setNext(LinkedList next){
		this.next = next;
	}
	
	public int countNodeList(){
		if(next != null){
			return 1 + next.countNodeList();
		}else{
			//次のノードがないため1を返す
			return 1;
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
		train.setVelocity(8.0);
		train.setDirection(Math.PI);

		LinkedList item3 = new LinkedList(airplane, null);
		LinkedList item2 = new LinkedList(train, item3);
		LinkedList item1 = new LinkedList(car, item2);

		System.out.println(item1.countNodeList());
		System.out.println(item3.countNodeList());
		System.out.println(item2.countNodeList());
	}
}
