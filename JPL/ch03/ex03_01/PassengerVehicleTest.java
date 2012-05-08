package ch03.ex03_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {
	//methodTest
	@Test
	public void testMethod() {
		PassengerVehicle car = new PassengerVehicle("Mike");
        car.setVelocity(2.0);
		car.setDirection(Math.PI / 2);
        car.setSeatCapacity(1);
        car.setRidingPerson(1);
        
        double expectedCapacity = 1;
        double expectedPerson = 1;
		assertEquals(new Double(expectedCapacity), new Double(car.getSeatCapacity()));
		assertEquals(new Double(expectedPerson), new Double(car.getRidingPerson()));
		
        PassengerVehicle train = new PassengerVehicle("Joe");
        train.setVelocity(4.0);
        train.setDirection(Math.PI);
        train.setSeatCapacity(1);
        train.setRidingPerson(1);
        
        expectedCapacity = 1;
        expectedPerson = 1;
		assertEquals(new Double(expectedCapacity), new Double(train.getSeatCapacity()));
		assertEquals(new Double(expectedPerson), new Double(train.getRidingPerson()));
		
        PassengerVehicle airplane = new PassengerVehicle("Nick");
        airplane.setVelocity(8.0);
        airplane.setDirection(Math.PI * 2);
        airplane.setSeatCapacity(4);
        airplane.setRidingPerson(2);
        
        expectedCapacity = 4;
        expectedPerson = 2;
		assertEquals(new Double(expectedCapacity), new Double(airplane.getSeatCapacity()));
		assertEquals(new Double(expectedPerson), new Double(airplane.getRidingPerson()));

	}
}
