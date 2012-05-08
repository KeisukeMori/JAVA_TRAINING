package ch04.ex04_03;

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
    	
		LinkedListImpl item3 = new LinkedListImpl(airplane, null);
		LinkedListImpl item2 = new LinkedListImpl(train, item3);
		LinkedListImpl item1 = new LinkedListImpl(car, item2);

		assertEquals(car, item1.getObj());
		assertEquals(train, item2.getObj());
		assertEquals(airplane, item3.getObj());

		
        // 複製        
		LinkedListImpl cloneList = (LinkedListImpl) item1.clone();
        assertNotSame(item1, cloneList);
        
        // 速度を変更してみる
        Vehicle tmpVehicle = (Vehicle)cloneList.getNextNode().getObj();
		tmpVehicle.setVelocity(100);
		train.setVelocity(100);
		assertEquals(new Double(train.getVelocity()), new Double(tmpVehicle.getVelocity()));
        
        
	}

}
