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
		ArrayList<User> result = new ArrayList<User>();
		int condition = 0;
		if(!ValidationRules.isEmpty(username))
			condition += 1000;
		if(!ValidationRules.isEmpty(firstName))
			condition += 100;
		if(!ValidationRules.isEmpty(lastName))
			condition += 10;
		if(!gender.equals("NONE"))
			condition += 1;
		System.out.println("THIS IS THE GENDER: " + gender);
		System.out.println("SEARCH CONDITION IS: " + condition);
		switch(condition) {
			case 1:
				result = findUsersByGender(gender);
				break;
			case 10:
				result = findUsersByLastName(lastName);
				break;
			case 11:
				result = findUsersByLastNameAndGender(lastName, gender);
				break;
			case 100:
				result = findUsersByFirstName(firstName);
				break;
			case 101:
				result = findUsersByFirstNameAndGender(firstName, gender);
				break;
			case 110:
				result = findUsersByFirstNameAndLastName(firstName, lastName);
				break;
			case 111:
				result = findUsersByFirstNameAndLastNameAndGender(firstName, lastName, gender);
				break;
			case 1000:
				result = findUsersByUsername(username);
				break;
			case 1001:
				result = findUsersByUsernameAndGender(username, gender);
				break;
			case 1010:
				result = findUsersByUsernameAndLastName(username, lastName);
				break;
			case 1011:
				result = findUsersByUsernameAndLastNameAndGender(username, lastName, gender);
				break;
			case 1100:
				result = findUsersByUsernameAndFirstName(username, firstName);
				break;
			case 1101:
				result = findUsersByUsernameAndFirstNameAndGender(username, firstName, gender);
				break;
			case 1110:
				result = findUsersByUsernameAndFirstNameAndLastName(username, firstName, lastName);
				break;
			case 1111:
				result = findUsersByUsernameAndFirstNameAndLastNameAndGender(username, firstName, lastName, gender);
				break;
			default:
				result = userList;
				break;
		}
		return result;
	}

	public static ArrayList<User> findUsersByUsernameAndFirstNameAndLastNameAndGender(String username,
			String firstName, String lastName, String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& firstName.equals(user.getFirstName())
						&& lastName.equals(user.getLastName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndFirstNameAndLastName(String username, String firstName,
			String lastName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& firstName.equals(user.getFirstName())
						&& lastName.equals(user.getLastName()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndFirstNameAndGender(String username, String firstName,
			String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& firstName.equals(user.getFirstName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndFirstName(String username, String firstName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& firstName.equals(user.getFirstName()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndLastNameAndGender(String username, String lastName,
			String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& lastName.equals(user.getLastName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndLastName(String username, String lastName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& lastName.equals(user.getLastName()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsernameAndGender(String username, String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByUsername(String username) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						username.equals(user.getUsername()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByFirstNameAndLastNameAndGender(String firstName, String lastName,
			String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						firstName.equals(user.getFirstName())
						&& lastName.equals(user.getLastName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByFirstNameAndLastName(String firstName, String lastName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						firstName.equals(user.getFirstName())
						&& lastName.equals(user.getLastName()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByFirstNameAndGender(String firstName, String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						firstName.equals(user.getFirstName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByFirstName(String firstName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						firstName.equals(user.getFirstName()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByLastNameAndGender(String lastName, String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						lastName.equals(user.getLastName())
						&& gender.equals(user.getGender().toString()))
				.collect(Collectors.toList())
		);
	}

	public static ArrayList<User> findUsersByLastName(String lastName) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						lastName.equals(user.getLastName()))
				.collect(Collectors.toList())
		);
	}
	
	public static ArrayList<User> findUsersByGender(String gender) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						gender.equals(user.getGender().toString()))
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
