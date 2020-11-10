package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dbm.AmenityDbModel;
import dbm.ApartmentAmenityDbModel;
import dbm.ApartmentDbModel;
import dbm.LocationDbModel;
import dbm.UserDbModel;
import model.Admin;
import model.Amenity;
import model.Apartment;
import model.Gender;
import model.Guest;
import model.Host;
import model.Location;
import model.Status;
import model.Type;
import model.User;
import validator.ValidationRules;

public class ContainerController {	
	//FIELDS
	
	//Container lists
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Location> locations = new ArrayList<Location>();
	public static ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	public static ArrayList<Amenity> amenities = new ArrayList<Amenity>();
	
	//METHODS
	
	//Populate lists
	public static void populateLists() {
		populateLocationsList();
		populateUserList();
		populateApartmentList();
		populateHostApartmentsUpForReservationList();
		populateAmenitiesList();
		populateApartmentAmenitiesList();
	}
	public static void populateLocationsList() {
		List<LocationDbModel> dbModels = DatabaseController.getLocationsFromDatabase();
	    locations = getLocationsFromDbModels(dbModels);
	}
	public static void populateUserList() {
		List<UserDbModel> dbModels = DatabaseController.getUsersFromDatabase();
		users = getUsersFromDbModels(dbModels);
	}
	public static void populateApartmentList() {
		List<ApartmentDbModel> dbModels = DatabaseController.getApartmentsFromDatabase();
		apartments = getApartmentsFromDbModels(dbModels);
	}
	public static void populateHostApartmentsUpForReservationList() {
		ArrayList<User> hosts = findUsersByRole("HOST");
		for(User host : hosts)
			((Host)host).setApartmentsUpForReservation(findApartmentsByHostId(host.getId()));
	}
	public static void populateAmenitiesList() {
		List<AmenityDbModel> dbModels = DatabaseController.getAmenitiesFromDatabase();
		amenities = getAmenitiesFromDbModels(dbModels);
	}
	public static void populateApartmentAmenitiesList() {
		List<ApartmentAmenityDbModel> dbModels = DatabaseController.getApartmentAmenityPairingsFromDatabase();
		Apartment apartment = null;
		Amenity amenity = null;
		for(ApartmentAmenityDbModel dbm : dbModels) {
			apartment = findApartmentById(Integer.parseInt(dbm.apartmentId));
			amenity = findAmenityById(Integer.parseInt(dbm.amenityId));
			apartment.getAmenities().add(amenity);
		}
	}

	//Get containerModel list from dbModel list
	private static ArrayList<Location> getLocationsFromDbModels(List<LocationDbModel> locationDbModels) {
		ArrayList<Location> list = new ArrayList<Location>();
		for(LocationDbModel dbm : locationDbModels) {
			list.add(createLocationFromModel(dbm));
		}
		return list;
	}
	private static ArrayList<User> getUsersFromDbModels(List<UserDbModel> userDbModels) {
		ArrayList<User> list = new ArrayList<User>();
		for(UserDbModel dbm : userDbModels) {
			switch(dbm.role) {
				case "ADMIN":
					list.add(createAdminFromModel(dbm));
					break;
				case "HOST":
					list.add(createHostFromModel(dbm));
					break;
				default:
					list.add(createGuestFromModel(dbm));	
			}
		}
		return list;
	}
	private static ArrayList<Apartment> getApartmentsFromDbModels(List<ApartmentDbModel> apartmentDbModels) {
		ArrayList<Apartment> list = new ArrayList<Apartment>();
		for(ApartmentDbModel dbm : apartmentDbModels) {
			list.add(createApartmentFromModel(dbm));
		}
		return list;
	}
	private static ArrayList<Amenity> getAmenitiesFromDbModels(List<AmenityDbModel> amenityDbModels) {
		ArrayList<Amenity> list = new ArrayList<Amenity>();
		for(AmenityDbModel dbm : amenityDbModels) {
			list.add(createAmenityFromModel(dbm));
		}
		return list;
	}
	
	//Create containerModel from dbModel
	private static Location createLocationFromModel(LocationDbModel dbm) {
		return new Location(
			Integer.parseInt(dbm.id),
			Float.parseFloat(dbm.latitude),
			Float.parseFloat(dbm.longitude),
			dbm.streetName,
			dbm.streetNumber,
			dbm.city,
			dbm.postNumber
		);
	}
	private static Admin createAdminFromModel(UserDbModel dbm) {
		return new Admin(
			Integer.parseInt(dbm.id),
			dbm.username,
			dbm.password,
			dbm.firstName,
			dbm.lastName,
			Gender.valueOf(dbm.gender),
			Boolean.valueOf(dbm.enabled)
		);
	}
	private static Host createHostFromModel(UserDbModel dbm) {
		return new Host(
			Integer.parseInt(dbm.id),
			dbm.username,
			dbm.password,
			dbm.firstName,
			dbm.lastName,
			Gender.valueOf(dbm.gender),
			Boolean.valueOf(dbm.enabled)
		);
	}
	private static Guest createGuestFromModel(UserDbModel dbm) {
		return new Guest(
			Integer.parseInt(dbm.id),
			dbm.username,
			dbm.password,
			dbm.firstName,
			dbm.lastName,
			Gender.valueOf(dbm.gender),
			Boolean.valueOf(dbm.enabled)
		);
	}
	private static Apartment createApartmentFromModel(ApartmentDbModel dbm) {
		return new Apartment(
			Integer.parseInt(dbm.id),
			Type.valueOf(dbm.type),
			Integer.parseInt(dbm.roomCount),
			Integer.parseInt(dbm.guestCount),
			ContainerController.findLocationById(Integer.parseInt(dbm.locationId)),
			(Host) ContainerController.findUserById(Integer.parseInt(dbm.hostId)),
			dbm.price,
			dbm.checkInTime,
			dbm.checkOutTime,
			Status.valueOf(dbm.status)
		);
	}
	private static Amenity createAmenityFromModel(AmenityDbModel dbm) {
		return new Amenity(
			Integer.parseInt(dbm.id),
			dbm.name,
			dbm.details
		);
	}
	
	//Save Lists
	public static void saveLocationList() {
		DatabaseController.saveLocationsToDatabase();
	}
	public static void saveUserList() {
		DatabaseController.saveUsersToDatabase();
	}
	public static void saveApartmentList() {
		DatabaseController.saveApartmentsToDatabase();
	}
	public static void saveAppartmentAmenitiyPairingsList() {
		DatabaseController.saveApartmentAmenityPairingsToDatabase();
	}

	//Find Location/Locations
	public static Location findLocationById(Integer id) {
		return locations.stream()
			.filter(
				location -> 
					id.equals(location.getId()))
			.findFirst()
			.orElse(null);
	}
	
	//Find User/Users
	public static User findUserById(Integer id) {
		return users.stream()
			.filter(
				user -> 
					id.equals(user.getId()))
			.findFirst()
			.orElse(null);
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
	public static ArrayList<User> findUsersByRole(String role) {
		return new ArrayList<>(
			users.stream()
				.filter(
					user -> 
						role.equals(user.getRole().toString()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<User> findUsersByOptionalUsernameAndOptionalFirstNameAndOptionalLastNameAndOptionalGender(
			ArrayList<User> userList, String username, String firstName, String lastName, String gender) {
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

	//Find Apartment/Apartments
	public static Apartment findApartmentById(Integer id) {
		return apartments.stream()
			.filter(
				apartment -> 
					id.equals(apartment.getId()))
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
		//TODO
		/*
		return new ArrayList<>(
				apartmentList.stream()
					.filter(
						apartment -> 
							apartment.getType().toString().contains(type))
					.filter(
						apartment -> 
							apartment.getGuestCount() <= Integer.parseInt(guestCount))
					.filter(
						apartment -> 
							apartment.getPrice().contains(price))
					.collect(Collectors.toList())
			);
		*/
		return null;
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

	//Find Amenity/Amenities
	public static Amenity findAmenityById(Integer id) {
		return amenities.stream()
			.filter(
				amenity -> 
					id.equals(amenity.getId()))
			.findFirst()
			.orElse(null);
	}
	
	//Checks
	public static boolean isAdmin(String role) {
		return role.equals("ADMIN");
	}
	public static boolean isHost(String role) {
		return role.equals("HOST");
	}
}
