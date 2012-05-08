package ch03.ex03_09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GarageTest {

	//methodTest
	@Test
	public void testMethod() {
        Garage garage = new Garage(5);
        
        Vehicle car = new Vehicle("Mike");
        car.setVelocity(2);
        garage.push(car);
        
        Vehicle train = new Vehicle("Joe");
        train.setVelocity(4);
        garage.push(train);
        
        Garage newGarage = (Garage)garage.clone();
        
        
		assertEquals(new Double(car.getVelocity()), new Double(newGarage.getBuffer()[0].getVelocity()));
		assertEquals(new Double(train.getVelocity()), new Double(newGarage.getBuffer()[1].getVelocity()));
	}

}
