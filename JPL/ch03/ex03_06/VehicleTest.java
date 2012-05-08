package ch03.ex03_06;

import static org.junit.Assert.*;
import org.junit.Test;


public class VehicleTest {
	//methodTest
	@Test
	public void testMethod() {
		Vehicle gasVehicle = new Vehicle(new GasTank(10.0));
		Vehicle noGasVehicle = new Vehicle(new GasTank(0.0));
		Vehicle batteryVehicle = new Vehicle(new Battery(100.0));
		Vehicle noBatteryVehicle = new Vehicle(new Battery(0.0));

		assertNotSame(gasVehicle, batteryVehicle);
		assertNotSame(noGasVehicle, noBatteryVehicle);
	}
}
