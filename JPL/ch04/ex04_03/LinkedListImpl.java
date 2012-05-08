package ch04.ex04_03;

public class LinkedListImpl implements Cloneable,LinkedList<LinkedListImpl>{
	private Object Obj;
	private LinkedListImpl nextNode;
	
	public LinkedListImpl(Object Obj,LinkedListImpl nextNode) {
		this.Obj = Obj;
		this.nextNode = nextNode;
	}
	
	public LinkedListImpl(Object Obj) {
		this(Obj,null);
	}
	
	public void show(){
		System.out.println(Obj.getClass());
		if (Obj instanceof Vehicle) {
			Vehicle tmpVehicle = (Vehicle) Obj;
			tmpVehicle.toString();
		}
		if(nextNode!=null)
			nextNode.show();
	}
	
	public String toString(){
		if (Obj instanceof Vehicle) {
			String tmpv;
			Vehicle tmpVehicle = (Vehicle) Obj;
			tmpv = tmpVehicle.toString();
			if(nextNode!=null)
				tmpv += nextNode.toString();
			return tmpv;
		}
		return null;
	}
	
	public Object getObj(){ return Obj;	}
	public void setObj(Object Obj){ this.Obj = Obj;	}
	
	public LinkedListImpl getNextNode(){return nextNode;}
	public void setNextNode(LinkedListImpl nextNode){this.nextNode = nextNode;}
	
	public int countList(){
		if(nextNode==null)
			return 1;
		else
			return 1 + nextNode.countList();
	}
	
	public LinkedListImpl clone(){
		try{
			LinkedListImpl ls = (LinkedListImpl)super.clone();
			if(ls.nextNode!=null)
				ls.nextNode = nextNode.clone();
			return ls;
		}catch(CloneNotSupportedException e){
			throw new InternalError(e.toString());
		}
	}
}


