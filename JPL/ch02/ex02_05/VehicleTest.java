package ch02.ex02_05;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	
	//idNumTest
	@Test
	public void testId() {
		Vehicle car = new Vehicle();
		assertEquals(0, (int)car.idNum);
		car.idNum = Vehicle.nextID++;
		
		Vehicle train = new Vehicle();
        train.idNum = Vehicle.nextID++; 
		assertEquals(1, (int)train.idNum);
		
		Vehicle airplane = new Vehicle();
		airplane.idNum = Vehicle.nextID++; 
		assertEquals(2, (int)airplane.idNum);
	}


}
