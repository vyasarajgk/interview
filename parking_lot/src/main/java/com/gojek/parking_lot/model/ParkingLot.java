package com.gojek.parking_lot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Parking lot with a fixed set of slots.
 * 
 * @author vyasraj
 *
 */
public class ParkingLot {
	private final List<Slot> slots;

	public ParkingLot(int size) {
		this.slots = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			slots.add(new Slot(i + 1));
		}
	}

	public List<Slot> getSlots() {
		return slots;
	}

	@Override
	public String toString() {
		return "ParkingLot [slots=" + slots + "]";
	}
}
