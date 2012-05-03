package ch02.ex02_11;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	//LinkedListTest
	@Test
	public void testLinkedList() {
		Vehicle car = new Vehicle("Mike");
		car.velocity = 2.0;
		car.direction = Math.PI * 2;
		
		Vehicle train = new Vehicle("Joe");
		train.velocity = 4.0;
		train.direction = Math.PI / 2;
		
		Vehicle airplane = new Vehicle("Nick");
		airplane.velocity = 8.0;
		airplane.direction = Math.PI;

        LinkedList item3 = new LinkedList(airplane, null);
        LinkedList item2 = new LinkedList(train, item3);
        LinkedList item1 = new LinkedList(car, item2);
        
		assertEquals("Mike", car.owner);
		assertEquals("Joe", train.owner);
		assertEquals("Nick", airplane.owner);
		
		assertEquals(car, item1.object);
		assertEquals(item2, item1.next);		
		
		assertEquals(train, item2.object);
		assertEquals(item3, item2.next);
		
		assertEquals(airplane, item3.object);
		assertEquals(null, item3.next);
	
	}

}
