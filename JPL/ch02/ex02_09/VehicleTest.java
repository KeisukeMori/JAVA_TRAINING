package ch02.ex02_09;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	
    public long idNum;
    public static long nextID = 0;
	
	//constructorTest
	@Test
	public void testConstructor() {
        Vehicle car = new Vehicle("Mike");
		assertEquals("Mike", car.owner);
		
		Vehicle train = new Vehicle("Joe");
		assertEquals("Joe", train.owner);
		
		Vehicle airplane = new Vehicle("Nick");
		assertEquals("Nick", airplane.owner);
	}
    
	//maxIdTest
	@Test
	public void testMaxId() {
		assertEquals(2, Vehicle.maxID());
	}
}