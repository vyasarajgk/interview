package com.gojek.parking_lot.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.gojek.parking_lot.model.Car;
import com.gojek.parking_lot.model.Slot;

/**
 * Abstracts APIs for accessing parking lot details.
 *
 * @author vyasraj
 *
 */
public interface IParkingLotDao {

	Optional<Slot> park(Car car);

	Car leave(int slotIndex);

	List<Slot> getFilledSlots();

	Set<Car> getCarsWithColor(String color);

	Car getCarWithRegNo(String regNo);
}
