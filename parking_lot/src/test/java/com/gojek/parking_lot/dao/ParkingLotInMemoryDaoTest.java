package com.gojek.parking_lot.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.gojek.parking_lot.dao.inmemory.ParkingLotInMemoryDao;
import com.gojek.parking_lot.model.Car;
import com.gojek.parking_lot.model.ParkingLot;
import com.gojek.parking_lot.model.Slot;

public class ParkingLotInMemoryDaoTest {

	@Test
	public void testPark() {
		ParkingLot lot = new ParkingLot(6);
		ParkingLotInMemoryDao dao = new ParkingLotInMemoryDao(lot);
		Optional<Slot> slot = dao.park(new Car("KA-01-HH-3141", "Blue"));
		Assert.assertEquals(1, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3142", "White"));
		Assert.assertEquals(2, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3143", "Black"));
		Assert.assertEquals(3, slot.get().getSlotNumber());

		List<Slot> slots = lot.getSlots();
		Assert.assertEquals("KA-01-HH-3141", slots.get(0).getCar().get().getRegNo());
		Assert.assertEquals("KA-01-HH-3142", slots.get(1).getCar().get().getRegNo());
		Assert.assertEquals("KA-01-HH-3143", slots.get(2).getCar().get().getRegNo());
		Assert.assertFalse(slots.get(3).getCar().isPresent());
		Assert.assertFalse(slots.get(4).getCar().isPresent());
		Assert.assertFalse(slots.get(5).getCar().isPresent());
	}

	@Test
	public void testParkAndLeave() {
		ParkingLot lot = new ParkingLot(6);
		ParkingLotInMemoryDao dao = new ParkingLotInMemoryDao(lot);
		Optional<Slot> slot = dao.park(new Car("KA-01-HH-3141", "Blue"));
		Assert.assertEquals(1, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3142", "White"));
		Assert.assertEquals(2, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3143", "Black"));
		Assert.assertEquals(3, slot.get().getSlotNumber());

		Car car = dao.leave(0);
		List<Slot> slots = lot.getSlots();
		Assert.assertEquals("KA-01-HH-3141", car.getRegNo());
		Assert.assertFalse(slots.get(0).getCar().isPresent());
		Assert.assertEquals("KA-01-HH-3142", slots.get(1).getCar().get().getRegNo());
		Assert.assertEquals("KA-01-HH-3143", slots.get(2).getCar().get().getRegNo());
	}

	@Test
	public void testGetFilledSlots() {
		ParkingLot lot = new ParkingLot(6);
		ParkingLotInMemoryDao dao = new ParkingLotInMemoryDao(lot);
		Optional<Slot> slot = dao.park(new Car("KA-01-HH-3141", "Blue"));
		Assert.assertEquals(1, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3142", "White"));
		Assert.assertEquals(2, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3143", "Black"));
		Assert.assertEquals(3, slot.get().getSlotNumber());

		List<Slot> slots = dao.getFilledSlots();

		Assert.assertEquals(1, slots.get(0).getSlotNumber());
		Assert.assertEquals(2, slots.get(1).getSlotNumber());
		Assert.assertEquals(3, slots.get(2).getSlotNumber());
	}

	@Test
	public void testGetCarsWithColor() {
		ParkingLot lot = new ParkingLot(6);
		ParkingLotInMemoryDao dao = new ParkingLotInMemoryDao(lot);
		Optional<Slot> slot = dao.park(new Car("KA-01-HH-3141", "Blue"));
		Assert.assertEquals(1, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3142", "White"));
		Assert.assertEquals(2, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3143", "Blue"));
		Assert.assertEquals(3, slot.get().getSlotNumber());

		Set<Car> cars = dao.getCarsWithColor("Blue");
		Assert.assertEquals(2, cars.size());
		Iterator<Car> iterator = cars.iterator();
		Assert.assertEquals("Blue", iterator.next().getColor());
		Assert.assertEquals("Blue", iterator.next().getColor());
	}

	@Test
	public void testGetCarWithRegNo() {
		ParkingLot lot = new ParkingLot(6);
		ParkingLotInMemoryDao dao = new ParkingLotInMemoryDao(lot);
		Optional<Slot> slot = dao.park(new Car("KA-01-HH-3141", "Blue"));
		Assert.assertEquals(1, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3142", "White"));
		Assert.assertEquals(2, slot.get().getSlotNumber());
		slot = dao.park(new Car("KA-01-HH-3143", "Blue"));
		Assert.assertEquals(3, slot.get().getSlotNumber());

		Car car = dao.getCarWithRegNo("KA-01-HH-3142");
		Assert.assertEquals("KA-01-HH-3142", car.getRegNo());
	}
}
