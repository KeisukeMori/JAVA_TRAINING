package ch02.ex02_15;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	
	//methodTest
	@Test
	public void testMethod() {
		Vehicle car = new Vehicle("Mike");
        car.setVelocity(2.0);
        double expectedVelocity = 2.0;
		assertEquals(new Double(expectedVelocity), new Double(car.getVelocity()));
		
		car.changeSpeed(4.0);
		expectedVelocity = 4.0;
		assertEquals(new Double(expectedVelocity), new Double(car.getVelocity()));
		car.stop();
		assertEquals(new Double(0), new Double(car.getVelocity()));		

		Vehicle train = new Vehicle("Joe");
        train.setVelocity(4.0);
        expectedVelocity = 4.0;
		assertEquals(new Double(expectedVelocity), new Double(train.getVelocity()));
		
		train.changeSpeed(8.0);
		expectedVelocity = 8.0;
		assertEquals(new Double(expectedVelocity), new Double(train.getVelocity()));
		train.stop();
		assertEquals(new Double(0), new Double(train.getVelocity()));	

		Vehicle airplane = new Vehicle("Nick");
        airplane.setVelocity(8.0);
        expectedVelocity = 8.0;
		assertEquals(new Double(expectedVelocity), new Double(airplane.getVelocity()));
		
		airplane.changeSpeed(16.0);
		expectedVelocity = 16.0;
		assertEquals(new Double(expectedVelocity), new Double(airplane.getVelocity()));
		airplane.stop();
		assertEquals(new Double(0), new Double(airplane.getVelocity()));
	}
}