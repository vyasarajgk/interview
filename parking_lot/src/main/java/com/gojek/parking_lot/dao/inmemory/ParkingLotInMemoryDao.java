package com.gojek.parking_lot.dao.inmemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.gojek.parking_lot.dao.IParkingLotDao;
import com.gojek.parking_lot.model.Car;
import com.gojek.parking_lot.model.ParkingLot;
import com.gojek.parking_lot.model.Slot;
import com.gojek.parking_lot.util.MinHeap;

public class ParkingLotInMemoryDao implements IParkingLotDao {
	private final ParkingLot parkingLot;
	private final MinHeap<Slot> freeSlots;
	private final Map<String, Set<Car>> colorToCars;
	private final Map<String, Car> regNoToCars;

	public ParkingLotInMemoryDao(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
		this.freeSlots = new MinHeap<Slot>(new Comparator<Slot>() {

			public int compare(Slot s1, Slot s2) {
				return s1.getSlotNumber() - s2.getSlotNumber();
			}
		}, parkingLot.getSlots().size());
		for (Slot slot : parkingLot.getSlots()) {
			freeSlots.add(slot);
		}
		this.colorToCars = new HashMap<String, Set<Car>>();
		this.regNoToCars = new HashMap<String, Car>();
	}

	public Optional<Slot> park(Car car) {
		Slot slot = freeSlots.remove();
		if (slot == null) {
			return Optional.empty();
		}

		if (regNoToCars.containsKey(car.getRegNo())) {
			throw new IllegalArgumentException("Car with registration no: " + car.getRegNo() + " already exists");
		}

		slot.parkCar(car);
		Set<Car> newSet = new HashSet<Car>();
		newSet.add(car);
		colorToCars.merge(car.getColor(), newSet, (set1, set2) -> {
			set1.addAll(set2);
			return set1;
		});
		regNoToCars.put(car.getRegNo(), car);
		return Optional.of(slot);
	}

	public Car leave(int slotIndex) {
		if (slotIndex >= parkingLot.getSlots().size()) {
			throw new IllegalArgumentException("Slot: " + slotIndex + " does not exist in the parking lot");
		}
		Slot slot = parkingLot.getSlots().get(slotIndex);
		Car car = slot.leaveCar();
		freeSlots.add(slot);
		Set<Car> cars = colorToCars.get(car.getColor());
		cars.remove(car);
		regNoToCars.remove(car.getRegNo());
		return car;
	}

	@Override
	public List<Slot> getFilledSlots() {
		List<Slot> slots = new ArrayList<>();
		for (Slot slot : parkingLot.getSlots()) {
			if (slot.getCar().isPresent()) {
				slots.add(slot);
			}
		}
		return slots;
	}

	@Override
	public Set<Car> getCarsWithColor(String color) {
		return colorToCars.get(color);
	}

	@Override
	public Car getCarWithRegNo(String regNo) {
		return regNoToCars.get(regNo);
	}
}
