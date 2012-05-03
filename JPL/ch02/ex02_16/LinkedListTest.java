package ch02.ex02_16;

import static org.junit.Assert.assertEquals;
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
		train.setVelocity(8.0);
		train.setDirection(Math.PI);

        LinkedList item3 = new LinkedList(airplane, null);
        LinkedList item2 = new LinkedList(train, item3);
        LinkedList item1 = new LinkedList(car, item2);
		
		assertEquals(car, item1.object);
		assertEquals(item2, item1.next);		
		
		assertEquals(train, item2.object);
		assertEquals(item3, item2.next);
		
		assertEquals(airplane, item3.object);
		assertEquals(null, item3.next);
		
		assertEquals(3, item1.countNodeList());
		assertEquals(1, item3.countNodeList());
		assertEquals(2, item2.countNodeList());
	}

}
