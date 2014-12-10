package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import cw3.Elevator;
import cw3.Customer;

public class ElevatorTest {
	
	// Set 1:
	
	@Test
	public void testGetNumFloors() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", floors, e.getNumFloors());
	}

	@Test
	public void testGetCustomerList() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		customerList.add(new Customer(1,4));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertTrue("Wrong answer: should be true.", e.getCustomerList().size() == 2);
	}

	@Test
	public void testSetAndGetCurrentFloor() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		customerList.add(new Customer(1,4));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		e.setCurrentFloor(3);
		assertTrue("Wrong answer: should be true.", e.getCurrentFloor() == 3);
	}

	@Test
	public void testGetTopFloor() {
		int floors = 5, bottomFloor = 0, topFloor = 4;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", topFloor, e.getTopFloor());
	}

	@Test
	public void testGetBottomFloor() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", bottomFloor, e.getBottomFloor());
	}

	@Test
	public void testGetDirection() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertTrue("Wrong answer: should be true.", e.getDirection().equals("up"));
	}

	@Test
	public void testIsAnnotations() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		e.setAnnotations(true);
		assertTrue("Wrong answer: should be true.", e.isAnnotations());
	}

	@Test
	public void testSetAllCustomersToNotFinished() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(4,0));
		customerList.add(new Customer(1,3));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		e.getCustomerList().get(0).setFinished(true);
		e.getCustomerList().get(1).setFinished(true);
		e.setAllCustomersToNotFinished();
		assertTrue("Wrong answer: should be true.", !e.getCustomerList().get(0).isFinished() && !e.getCustomerList().get(1).isFinished());
	}

	@Test
	public void testStartElevatorDefaultStrategy() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		customerList.add(new Customer(1,2));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", 8, e.startElevatorDefaultStrategy());
	}

	@Test
	public void testStartElevatorImprovedStrategy() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		customerList.add(new Customer(1,2));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", 2, e.startElevatorImprovedStrategy());
	}
	
	// Set 2:
	
	@Test
	public void testGetNumFloorsIllegal() {
		int floors = 1, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertTrue("Wrong answer: should be true.", e.getNumFloors() != floors);
	}
	
	@Test
	public void testIsAnnotations2() {
		int floors = 5, bottomFloor = 0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(0,1));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		e.setAnnotations(false);
		assertTrue("Wrong answer: should be true.", !e.isAnnotations());
	}

	@Test
	public void testStartElevatorDefaultStrategy2() {
		int floors = 10, bottomFloor = -2;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1,0));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", 18, e.startElevatorDefaultStrategy());
	}

	@Test
	public void testStartElevatorImprovedStrategy2() {
		int floors = 10, bottomFloor = -2;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1,0));
		Elevator e = new Elevator(floors, bottomFloor, customerList);
		assertEquals("Wrong answer: should be equal.", 4, e.startElevatorImprovedStrategy());
	}
}
