package ch03.ex03_10;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	//LinkedListTest
	@Test
	public void testLinkedList() {
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

		assertEquals(car, item1.getObj());
		assertEquals(train, item2.getObj());
		assertEquals(airplane, item3.getObj());

		
        // 複製        
        LinkedList cloneList = (LinkedList) item1.clone();
        assertNotSame(item1, cloneList);
        
        // 速度を変更してみる
        Vehicle tmpVehicle = (Vehicle)cloneList.getNextNode().getObj();
		tmpVehicle.setVelocity(100);
		train.setVelocity(100);
		assertEquals(new Double(train.getVelocity()), new Double(tmpVehicle.getVelocity()));
        
        
	}

}
