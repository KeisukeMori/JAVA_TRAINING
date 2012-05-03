package ch02.ex02_06;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class LinkedListTest {
	//LinkedListTest
	@Test
	public void testLinkedList() {
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
        
        
		assertEquals("Mike", item1.vehicle.owner);
		assertEquals(1234, item1.object);
		assertEquals(item2, item1.next);		
		
		assertEquals("Joe", item2.vehicle.owner);
		assertEquals(3456, item2.object);
		assertEquals(item3, item2.next);
		
		assertEquals("Nick", item3.vehicle.owner);
		assertEquals(7890, item3.object);
		assertEquals(null, item3.next);


	}
	

}
