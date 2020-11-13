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

	//Save Lists
	public static void saveLocationList() {
		List<LocationDbModel> dbModels = getDbModelsFromLocations();
		DatabaseController.saveLocationsToDatabase(dbModels);
	}
	public static void saveUserList() {
		List<UserDbModel> dbModels = getDbModelsFromUsers();
		DatabaseController.saveUsersToDatabase(dbModels);
	}
	public static void saveApartmentList() {
		List<ApartmentDbModel> dbModels = getApartmentDbModelsFromApartments();
		DatabaseController.saveApartmentsToDatabase(dbModels);
	}
	public static void saveAppartmentAmenitiyPairingList() {
		List<ApartmentAmenityDbModel> dbModels = getApartmentAmenityDbModelsFromApartments();
		DatabaseController.saveApartmentAmenityPairingsToDatabase(dbModels);
	}
	public static void saveAmenityList() {
		List<AmenityDbModel> dbModels = getDbModelsFromAmenities();
		DatabaseController.saveAmenitiesToDatabase(dbModels);	
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
			findLocationById(Integer.parseInt(dbm.locationId)),
			(Host) findUserById(Integer.parseInt(dbm.hostId)),
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
			dbm.details,
			Boolean.parseBoolean(dbm.enabled)
		);
	}
	
	//Get dbModel list from container list
	private static List<LocationDbModel> getDbModelsFromLocations() {
		ArrayList<LocationDbModel> dbModels = new ArrayList<LocationDbModel>();
		for(Location l : locations) {
			LocationDbModel dbm = createModelFromLocation(l);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static List<UserDbModel> getDbModelsFromUsers() {
		ArrayList<UserDbModel> dbModels = new ArrayList<UserDbModel>();
		for(User u : users) {
			UserDbModel dbm = createModelFromUser(u);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static List<ApartmentDbModel> getApartmentDbModelsFromApartments() {
		ArrayList<ApartmentDbModel> dbModels = new ArrayList<ApartmentDbModel>();
		for(Apartment a : apartments) {
			ApartmentDbModel dbm = createModelFromApartment(a);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static List<ApartmentAmenityDbModel> getApartmentAmenityDbModelsFromApartments() {
		ArrayList<ApartmentAmenityDbModel> dbModels = new ArrayList<ApartmentAmenityDbModel>();
		Integer id = 1;
		for(Apartment apartment : apartments) {
			for(Amenity amenity : apartment.getAmenities()) {
				ApartmentAmenityDbModel dbm = createModelFromApartmentAndAmenity(id, apartment.getId(), amenity.getId());
				dbModels.add(dbm);
				id = new Integer(id.intValue() + 1);
			}
		}
		return dbModels;
	}
	private static List<AmenityDbModel> getDbModelsFromAmenities() {
		ArrayList<AmenityDbModel> dbModels = new ArrayList<AmenityDbModel>();
		for(Amenity a : amenities) {
			AmenityDbModel dbm = createModelFromAmenity(a);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	
	//Create dbModel from containerModel/containerModels
	public static LocationDbModel createModelFromLocation(Location l) {
		return new LocationDbModel(
			l.getId().toString(),
			l.getLatitude().toString(),
			l.getLongitude().toString(),
			l.getStreetName(),
			l.getStreetNumber(),
			l.getCity(),
			l.getPostNumber()
		);
	}
	public static UserDbModel createModelFromUser(User u) {
		return new UserDbModel(
			u.getId().toString(),
			u.getUsername(),
			u.getPassword(),
			u.getFirstName(),
			u.getLastName(),
			u.getGender().toString(),
			u.getRole().toString(),
			u.getEnabled().toString()
		);
	}
	public static ApartmentDbModel createModelFromApartment(Apartment a) {
		return new ApartmentDbModel(
			a.getId().toString(),
			a.getType().toString(),
			a.getRoomCount().toString(),
			a.getGuestCount().toString(),
			a.getLocation().getId().toString(),
			a.getHost().getId().toString(),
			a.getPrice().toString(),
			a.getCheckInTime(),
			a.getCheckOutTime(),
			a.getStatus().toString());
	}
	public static ApartmentAmenityDbModel createModelFromApartmentAndAmenity(
			Integer id, 
			Integer apartmentId,
			Integer amenityId) {
			return new ApartmentAmenityDbModel(
				id.toString(), 
				apartmentId.toString(), 
				amenityId.toString()
			);
		}
	public static AmenityDbModel createModelFromAmenity(Amenity a) {
		return new AmenityDbModel(
			a.getId().toString(),
			a.getName(),
			a.getDetails(),
			a.getEnabled().toString()
		);
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
	public static ArrayList<Amenity> findAmenitiesByEnabled(Boolean enabled) {
		return new ArrayList<>(
				amenities.stream()
					.filter(
						amenity -> 
							enabled.equals(amenity.getEnabled()))
					.collect(Collectors.toList())
			);
	}
	
	//Checks
	public static boolean isAdmin(String role) {
		return role.equals("ADMIN");
	}
	public static boolean isHost(String role) {
		return role.equals("HOST");
	}

	//Logical Delete
	public static void logicalDeleteAmenity(Amenity amenity) {
		amenity.setEnabled(false);
		for(Apartment apartment : apartments) {
			if(apartment.getAmenities().contains(amenity))
			apartment.getAmenities().remove(amenity);
		}
	}
}
