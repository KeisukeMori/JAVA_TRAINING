package ch02.ex02_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {
	
    public long idNum;
    public static long nextID = 0;
	
	//idNumTest
	@Test
	public void testId() {
		Vehicle car = new Vehicle();
		assertEquals(0, (int)car.idNum);
		
		Vehicle train = new Vehicle();
		assertEquals(1, (int)train.idNum);
		
		Vehicle airplane = new Vehicle();
		assertEquals(2, (int)airplane.idNum);
	}
	
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
	
}