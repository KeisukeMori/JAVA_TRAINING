package ch03.ex03_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {
	//methodTest
	@Test
	public void testMethod() {
		PassengerVehicle car = new PassengerVehicle("Mike");
		car.setVelocity(2);
		car.setSeatCapacity(1);
		car.setRidingPerson(1);
		System.out.println(car + "\n");

		PassengerVehicle copy = (PassengerVehicle) car.clone();
		
		assertEquals(new Double(car.getVelocity()), new Double(copy.getVelocity()));
		assertEquals(new Double(car.getSeatCapacity()), new Double(copy.getSeatCapacity()));
		assertEquals(new Double(car.getRidingPerson()), new Double(copy.getRidingPerson()));
	}
}
