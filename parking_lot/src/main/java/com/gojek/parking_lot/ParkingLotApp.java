package com.gojek.parking_lot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.gojek.parking_lot.dao.IParkingLotDao;
import com.gojek.parking_lot.dao.inmemory.ParkingLotInMemoryDao;
import com.gojek.parking_lot.model.Car;
import com.gojek.parking_lot.model.ParkingLot;
import com.gojek.parking_lot.model.Slot;

/**
 * App to run Parking Lot Simulation
 *
 */
public class ParkingLotApp {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in);
		IParkingLotDao parkingLot = null;
		while (sc.hasNextLine()) {
			String command = sc.next();
			switch (command) {
			case "create_parking_lot":
				int size = sc.nextInt();
				parkingLot = new ParkingLotInMemoryDao(new ParkingLot(size));
				System.out.printf("Created a parking lot with %d slots\n", size);
				break;
			case "park":
				String regNo = sc.next();
				String color = sc.next();
				Optional<Slot> slot = parkingLot.park(new Car(regNo, color));
				if (slot.isPresent()) {
					System.out.printf("Allocated slot number: %d\n", slot.get().getSlotNumber());
				} else {
					System.out.println("Sorry, parking lot is full");
				}
				break;
			case "leave":
				int slotIndex = sc.nextInt();
				try {
					parkingLot.leave(slotIndex - 1);
				} catch (IllegalStateException e) {
					System.out.printf("Slot number %d is already free\n", slotIndex);
				}
				System.out.printf("Slot number %d is free\n", slotIndex);
				break;
			case "status":
				System.out.println(status(parkingLot.getFilledSlots()));
				break;
			case "registration_numbers_for_cars_with_colour":
				color = sc.next();
				Set<Car> cars = parkingLot.getCarsWithColor(color);
				if (cars != null && !cars.isEmpty()) {
					System.out.println(
							String.join(", ", cars.stream().map(car -> car.getRegNo()).collect(Collectors.toList())));
				} else {
					System.out.println("Not found");
				}
				break;
			case "slot_numbers_for_cars_with_colour":
				color = sc.next();
				cars = parkingLot.getCarsWithColor(color);
				if (cars != null && !cars.isEmpty()) {
					System.out.println(String.join(", ",
							cars.stream().map(car -> String.valueOf(car.getSlot().get().getSlotNumber()))
									.collect(Collectors.toList())));
				} else {
					System.out.println("Not found");
				}
				break;
			case "slot_number_for_registration_number":
				regNo = sc.next();
				Car car = parkingLot.getCarWithRegNo(regNo);
				if (car != null) {
					System.out.println(car.getSlot().get().getSlotNumber());
				} else {
					System.out.println("Not found");
				}
				break;
			}
		}
	}

	public static List<String> regNoOfCars(Set<Car> cars) {
		List<String> regNoList = new ArrayList<>();
		for (Car car : cars) {
			regNoList.add(car.getRegNo());
		}
		return regNoList;
	}

	public static String status(List<Slot> slots) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.join("\t", "Slot No.", "Registration No", "Colour"));

		for (Slot slot : slots) {
			builder.append("\n");
			builder.append(String.join("\t", String.valueOf(slot.getSlotNumber()), slot.getCar().get().getRegNo(),
					slot.getCar().get().getColor()));

		}

		return builder.toString();
	}
}
