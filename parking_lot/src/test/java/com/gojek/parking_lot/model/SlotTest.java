package com.gojek.parking_lot.model;

import org.junit.Assert;
import org.junit.Test;

public class SlotTest {

	@Test
	public void testSlotPark() {
		Slot slot = new Slot(1);
		Assert.assertEquals(1, slot.getSlotNumber());
		Assert.assertFalse(slot.getCar().isPresent());
		Car car = new Car("KA-01-HH-3142", "White");
		slot.parkCar(car);
		Assert.assertEquals("White", slot.getCar().get().getColor());
	}

	@Test(expected = IllegalStateException.class)
	public void testSlotLeaveIfAlreadyFree() {
		Slot slot = new Slot(1);
		Assert.assertEquals(1, slot.getSlotNumber());
		Assert.assertFalse(slot.getCar().isPresent());
		slot.leaveCar();
	}

	@Test
	public void testSlotLeave() {
		Slot slot = new Slot(1);
		Assert.assertEquals(1, slot.getSlotNumber());
		Assert.assertFalse(slot.getCar().isPresent());
		Car car = new Car("KA-01-HH-3142", "White");
		slot.parkCar(car);
		Car returnCar = slot.leaveCar();
		Assert.assertEquals(car, returnCar);
		Assert.assertFalse(slot.getCar().isPresent());
	}
}
