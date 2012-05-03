package ch02.ex02_17;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	//methodTest
	@Test
	public void testMethod() {
		Vehicle car = new Vehicle("Mike");
		car.setVelocity(2.0);
		car.setDirection(Math.PI / 2);
		
		double expectedDirection = Math.PI / 2;
		assertEquals(new Double(expectedDirection), new Double(car.getDirection()));

		car.turn(Math.PI / 2);
		
		expectedDirection = Math.PI;
		assertEquals(new Double(expectedDirection), new Double(car.getDirection()));

		car.turn(0);
		expectedDirection = Math.PI - (Math.PI / 2);
		assertEquals(new Double(expectedDirection), new Double(car.getDirection()));

		car.turn(1);
		expectedDirection = expectedDirection + (Math.PI / 2);
		assertEquals(new Double(expectedDirection), new Double(car.getDirection()));
	}
}
