package controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Apartment;
import model.Guest;
import model.Host;
import model.Location;
import model.User;
import validator.ValidationRules;

public class ContainerController {
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Location> locations = new ArrayList<Location>();
	public static ArrayList<Apartment> apartments = new ArrayList<Apartment>();

	public static void populateLists() {
		populateLocationsList();
		populateUserList();
		populateApartmentList();
		populateHostApartmentsUpForReservationList();
	}
	
	public static void populateHostApartmentsUpForReservationList() {
		ArrayList<User> hosts = findUsersByRole("HOST");
		for(User host : hosts)
			((Host)host).setApartmentsUpForReservation(findApartmentsByHostId(host.getId()));
	}

	public static ArrayList<Apartment> findApartmentsByHostId(Integer id) {
		return new ArrayList<>(
			apartments.stream()
				.filter(
					apartment -> 
						id.equals(apartment.getHost().getId()))
				.collect(Collectors.toList())
		);
	}

	public static void populateLocationsList() {
		locations = new ArrayList<Location>();
		locations = DatabaseController.getLocationsFromDatabase();
	}

	public static void populateUserList() {
		users = new ArrayList<User>();
		users = DatabaseController.getUsersFromDatabase();
	}

	public static void saveUserList() {
		DatabaseController.saveUsersToDatabase();
	}

	public static void addUser(Guest guest) {
		users.add(guest);
	}

	public static User findUserByUsernameAndPassword(String username, String password) {
		return users.stream()
			.filter(
				user -> 
					username.equals(user.getUsername())
					&& password.equals(user.getPassword()))
			.findFirst()
			.orElse(null);
	}

	public static User findUserById(Integer id) {
		return users.stream()
			.filter(
				user -> 
					id.equals(user.getId()))
			.findFirst()
			.orElse(null);
	}
	
	public static ArrayList<User> findUsersByRole(String role) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						role.equals(user.getRole().toString()))
				.collect(Collectors.toList())
		);
	}
	
	public static ArrayList<User> findUsersByOptionalUsernameAndOptionalFirstNameAndOptionalLastNameAndOptionalGender(ArrayList<User> userList, String username,
			String firstName, String lastName, String gender) {
		return new ArrayList<>(
			userList.stream()
				.filter(
					user -> 
						user.getUsername().contains(username))
				.filter(
					user -> 
						user.getFirstName().contains(firstName))
				.filter(
					user -> 
						user.getLastName().contains(lastName))
				.filter(
					user -> 
						(gender.equals("NONE")) ? true : user.getGender().toString().equals(gender))
				.collect(Collectors.toList())
		);
	}

	public static boolean isAdmin(String role) {
		return role.equals("ADMIN");
	}
	
	public static boolean isHost(String role) {
		return role.equals("HOST");
	}
	
	public static void populateApartmentList() {
		apartments = new ArrayList<Apartment>();
		apartments = DatabaseController.getApartmentsFromDatabase();
	}

	public static Location findLocationById(Integer id) {
		return locations.stream()
			.filter(
				location -> 
					id.equals(location.getId()))
			.findFirst()
			.orElse(null);
	}

	public static ArrayList<Apartment> findApartmentsByStatus(String status) {
		return new ArrayList<>(
			apartments.stream()
				.filter(
					apartment -> 
						status.equals(apartment.getStatus().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<Apartment> findApartmentsByOptionalTypeAndAndOptionalGuestCountAndOptionalPriceLessThan(
			ArrayList<Apartment> apartmentList, String type, String guestCount, String price) {
		// TODO Auto-generated method stub
		return null;
	}
}
