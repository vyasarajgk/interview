package com.gojek.parking_lot.model;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

	@Test
	public void testCarAssignSlot() {
		Car car = new Car("KA-01-HH-3142", "White");
		Assert.assertEquals("KA-01-HH-3142", car.getRegNo());
		Assert.assertEquals("White", car.getColor());
		Assert.assertFalse(car.getSlot().isPresent());
		car.assignSlot(new Slot(1));
		Assert.assertEquals(1, car.getSlot().get().getSlotNumber());
	}

	@Test
	public void testCarRemoveFromSlot() {
		Car car = new Car("KA-01-HH-3142", "White");
		Assert.assertEquals("KA-01-HH-3142", car.getRegNo());
		Assert.assertEquals("White", car.getColor());
		Assert.assertFalse(car.getSlot().isPresent());
		car.assignSlot(new Slot(1));
		Assert.assertEquals(1, car.getSlot().get().getSlotNumber());
		car.removeFromSlot();
		Assert.assertFalse(car.getSlot().isPresent());
	}
}
