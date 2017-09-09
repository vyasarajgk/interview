package com.gojek.parking_lot.model;

import java.util.Optional;

/**
 * Represents one slot in a parking lot identified by a slot number, which may
 * have a car parked and car field is set other the slot is considered
 * available.
 * 
 * @author vyasraj
 *
 */
public class Slot {
	private final int slotNumber;
	Optional<Car> car;

	public Slot(int slotNumber) {
		this.slotNumber = slotNumber;
		this.car = Optional.empty();
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public Optional<Car> getCar() {
		return car;
	}

	public void parkCar(Car car) {
		if (!this.car.isPresent()) {
			this.car = Optional.of(car);
			car.assignSlot(this);
		} else {
			throw new IllegalStateException("Slot " + slotNumber + " already has a car: " + this.car.get());
		}
	}

	public Car leaveCar() {
		if (this.car.isPresent()) {
			Car car = this.car.get();
			this.car.get().removeFromSlot();
			this.car = Optional.empty();
			return car;
		} else {
			throw new IllegalStateException("Slot " + slotNumber + " is already free");
		}
	}

	@Override
	public String toString() {
		return "Slot [slotNumber=" + slotNumber + ", isPresent=" + car.isPresent() + ", car=" + car + "]";
	}
}
