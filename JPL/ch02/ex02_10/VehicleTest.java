package ch02.ex02_10;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {
	
    public long idNum;
    public static long nextID = 0;
    
	//toStringTest
	@Test
	public void testToString() {
        Vehicle car = new Vehicle("Mike");
        car.velocity = 2.0;
        car.direction = Math.PI / 2;
        String expected = "ID = 0" + "\n" 
            + "スピード = 2.0" + "\n"  
            +"方向 = 1.5707963267948966" + "\n"
            +"所有者 = Mike"; 
        String actual = car.toString();
        assertEquals(expected, actual);

        Vehicle train = new Vehicle("Joe");
        train.velocity = 4.0;
        train.direction = Math.PI * 2;
        expected = "ID = 1" + "\n" 
        + "スピード = 4.0" + "\n"  
        +"方向 = 6.283185307179586" + "\n"
        +"所有者 = Joe"; 
        actual = train.toString();
        assertEquals(expected, actual);
        
        Vehicle airplane = new Vehicle("Nick");
        airplane.velocity = 8.0;
        airplane.direction = Math.PI;
        expected = "ID = 2" + "\n" 
        + "スピード = 8.0" + "\n"  
        +"方向 = 3.141592653589793" + "\n"
        +"所有者 = Nick"; 
        actual = airplane.toString();
        assertEquals(expected, actual);
	}
}