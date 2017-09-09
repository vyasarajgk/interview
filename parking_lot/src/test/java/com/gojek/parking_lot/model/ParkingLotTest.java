package com.gojek.parking_lot.model;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

	@Test
	public void testParkingLotCreation() {
		ParkingLot parkingLot = new ParkingLot(6);
		Assert.assertEquals(6, parkingLot.getSlots().size());
		Assert.assertEquals(1, parkingLot.getSlots().get(0).getSlotNumber());
		Assert.assertEquals(6, parkingLot.getSlots().get(5).getSlotNumber());
	}

}
