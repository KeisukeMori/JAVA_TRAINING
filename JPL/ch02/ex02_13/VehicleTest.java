package ch02.ex02_13;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	
	//methodTest
	@Test
	public void testMethod() {
		Vehicle car = new Vehicle("Mike");
        car.setVelocity(2.0);
        car.setDirection(Math.PI / 2); 

        double expectedVelocity = 2.0;
		assertEquals(new Double(expectedVelocity), new Double(car.getVelocity()));
		double expectedDirection = Math.PI / 2;
		assertEquals(new Double(expectedDirection), new Double(car.getDirection()));

		Vehicle train = new Vehicle("Joe");
        train.setVelocity(4.0);
        train.setDirection(Math.PI * 2); 

        expectedVelocity = 4.0;
		assertEquals(new Double(expectedVelocity), new Double(train.getVelocity()));
		expectedDirection = Math.PI * 2;
		assertEquals(new Double(expectedDirection), new Double(train.getDirection()));

		Vehicle airplane = new Vehicle("Nick");
        airplane.setVelocity(8.0);
        airplane.setDirection(Math.PI); 

        expectedVelocity = 8.0;
		assertEquals(new Double(expectedVelocity), new Double(airplane.getVelocity()));
		expectedDirection = Math.PI;
		assertEquals(new Double(expectedDirection), new Double(airplane.getDirection()));
	}
}