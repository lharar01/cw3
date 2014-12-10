package test;

import static org.junit.Assert.*;
import org.junit.Test;
import cw3.Customer;

public class CustomerTest {
	
	// Set 1:
	
	@Test
	public void testGetCurrentFloor() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertEquals("Wrong answer: should be equal.", currentFloor, c.getCurrentFloor());
	}

	@Test
	public void testGetDestinationFloor() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertEquals("Wrong answer: should be equal.", destinationFloor, c.getDestinationFloor());
	}

	@Test
	public void testGetId() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c1 = new Customer(currentFloor, destinationFloor);
		Customer c2 = new Customer(currentFloor, destinationFloor);
		assertTrue("Wrong answer: should be true.", c1.getId() + 1 == c2.getId());
	}

	@Test
	public void testSetAndIsInElevator() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		c.setInElevator(true);
		assertTrue("Wrong answer: should be true.", c.isInElevator());
	}

	@Test
	public void testSetAndIsFinished() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		c.setFinished(true);
		assertTrue("Wrong answer: should be true.", c.isFinished());
	}

	@Test
	public void testCalcDirection() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertTrue("Wrong answer: should be true.", c.calcDirection().equals("up"));
	}
	
	// Set 2:
	
	@Test
	public void testGetCurrentFloor2() {
		int currentFloor = 5, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertEquals("Wrong answer: should be equal.", currentFloor, c.getCurrentFloor());
	}

	@Test
	public void testGetDestinationFloor2() {
		int currentFloor = 0, destinationFloor = 5;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertEquals("Wrong answer: should be equal.", destinationFloor, c.getDestinationFloor());
	}

	@Test
	public void testGetId2() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c1 = new Customer(currentFloor, destinationFloor);
		Customer c2 = new Customer(currentFloor+1, destinationFloor+1);
		c2 = new Customer(currentFloor+2, destinationFloor+2);
		assertEquals("Wrong answer: should be equals.", c1.getId() + 2, c2.getId());
	}

	@Test
	public void testIsInElevator() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertTrue("Wrong answer: should be true.", !c.isInElevator());
	}

	@Test
	public void testIsFinished() {
		int currentFloor = 0, destinationFloor = 1;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertTrue("Wrong answer: should be true.", !c.isFinished());
	}

	@Test
	public void testCalcDirectionIllegal() {
		int currentFloor = 0, destinationFloor = 0;
		Customer c = new Customer(currentFloor, destinationFloor);
		assertTrue("Wrong answer: should be true.", c.calcDirection().equals("undetermined"));
	}
}
