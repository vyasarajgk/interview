package com.gojek.parking_lot.model;

import java.util.Optional;

/**
 * Represents a car which may be parked, in which case the slot will be
 * assigned.
 * 
 * @author vyasraj
 *
 */
public class Car {
	private final String regNo;
	private final String color;
	Optional<Slot> slot;

	public Car(String regNo, String color) {
		this.regNo = regNo;
		this.color = color;
		this.slot = Optional.empty();
	}

	public String getRegNo() {
		return regNo;
	}

	public String getColor() {
		return color;
	}

	public Optional<Slot> getSlot() {
		return slot;
	}

	public void assignSlot(Slot slot) {
		this.slot = Optional.of(slot);
	}

	public void removeFromSlot() {
		this.slot = Optional.empty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((regNo == null) ? 0 : regNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (regNo == null) {
			if (other.regNo != null)
				return false;
		} else if (!regNo.equals(other.regNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [regNo=" + regNo + ", color=" + color + "]";
	}
}
