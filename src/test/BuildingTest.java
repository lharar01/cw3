package test;

import static org.junit.Assert.*;

//import org.junit.BeforeClass;
import org.junit.Test;

import cw3.Building;

public class BuildingTest {
	
	// Set 1:
	
	@Test
	public void testGetNumFloors() {
		int floors = 5, customers = 10, bottomFloor = -1;
		Building building = new Building(floors, customers, bottomFloor);
		assertEquals("Wrong answer: should be equal.", floors, building.getNumFloors());
	}

	@Test
	public void testGetBottomFloor() {
		int floors = 5, customers = 10, bottomFloor = -1;
		Building building = new Building(floors, customers, bottomFloor);
		assertEquals("Wrong answer: should be equal.", bottomFloor, building.getBottomFloor());
	}

	@Test
	public void testGetCustomerList() {
		int floors = 5, customers = 10, bottomFloor = -1;
		Building building = new Building(floors, customers, bottomFloor);
		assertEquals("Wrong answer: should be equal.", customers, building.getCustomerList().size());
	}
	
	// Set 2:
	
	@Test
	public void testGetNumFloorsIllegal() {
		int floors = 1, customers = 0, bottomFloor = 0;
		Building building = new Building(floors, customers, bottomFloor);
		assertTrue("Wrong answer: should be true.", floors != building.getNumFloors());
	}

	@Test
	public void testGetBottomFloor2() {
		int floors = 0, customers = 5, bottomFloor = 0;
		Building building = new Building(floors, customers, bottomFloor);
		assertTrue("Wrong answer: should be true.", building.getBottomFloor() == bottomFloor);
	}

	@Test
	public void testGetCustomerListIllegal() {
		int floors = 5, customers = -1, bottomFloor = 0;
		Building building = new Building(floors, customers, bottomFloor);
		assertEquals("Wrong answer: should be equal.", 0, building.getCustomerList().size());
	}
}