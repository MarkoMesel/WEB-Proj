package controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

import dbm.AmenityDbModel;
import dbm.ApartmentPairingDbModel;
import dbm.ApartmentDbModel;
import dbm.CommentDbModel;
import dbm.LocationDbModel;
import dbm.ReservationDbModel;
import dbm.UserDbModel;
import model.Admin;
import model.Amenity;
import model.Apartment;
import model.Gender;
import model.Guest;
import model.Host;
import model.Location;
import model.Reservation;
import model.ReservationStatus;
import model.Role;
import model.ApartmentStatus;
import model.Comment;
import model.Type;
import model.User;
import tablemodel.AmenityTableModel;
import tablemodel.ApartmentTableModel;
import tablemodel.ApartmentTableParameter;
import tablemodel.CommentTableModel;
import tablemodel.ReservationTableModel;
import tablemodel.ReservationTableParameter;
import helper.PriceManager;
import helper.ComparisonOption;

public class ContainerController {	
	//FIELDS
	
	//Container lists
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Location> locations = new ArrayList<Location>();
	public static ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	public static ArrayList<Amenity> amenities = new ArrayList<Amenity>();
	public static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	public static ArrayList<Comment> comments = new ArrayList<Comment>();
	//public static HashMap<Apartment, ArrayList<String>> apartmentPictureReferencePairings = new HashMap<Apartment, ArrayList<String>>();
	
	//METHODS
	
	//Populate lists
	public static void populateLists() {
		populateLocationsList();
		populateUserList();
		populateApartmentList();
		populateHostApartmentsUpForReservationList();
		populateAmenitiesList();
		populateApartmentAmenitiesList();
		populateReservationsList();
		populateGuestReservationsAndReservedApartmentsList();
		populateApartmentBusyDatesList();
		populateCommentsList();
		populateApartmentPictureReferenceList();
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
		List<ApartmentPairingDbModel> dbModels = DatabaseController.getApartmentPairingsFromDatabase("apartmentAmenityPairings.txt");
		Apartment apartment = null;
		Amenity amenity = null;
		for(ApartmentPairingDbModel dbm : dbModels) {
			apartment = findApartmentById(Integer.parseInt(dbm.apartmentId));
			amenity = findAmenityById(Integer.parseInt(dbm.strPair));
			apartment.getAmenities().add(amenity);
		}
	}
	public static void populateReservationsList() {
		List<ReservationDbModel> dbModels = DatabaseController.getReservationsFromDatabase();
		reservations = getReservationsFromDbModels(dbModels);
	}
	public static void populateGuestReservationsAndReservedApartmentsList() {
		ArrayList<User> guests = findUsersByRole(Role.GUEST.toString());
		for(User guest : guests) {
			ArrayList<Reservation> reservations = findReservationsByGuestId(guest.getId());
			((Guest)guest).setReservations(reservations);
			ArrayList<Apartment> apartments = new ArrayList<>();
			for(Reservation reservation : reservations) {
				if(!apartments.contains(reservation.getApartment())) {
					apartments.add(reservation.getApartment());
				}
			}
			((Guest)guest).setReservedApartments(apartments);
		}
	}
	public static void populateApartmentBusyDatesList() {
		for(Apartment apartment : apartments) {
			ArrayList<Reservation> reservations = findReservationsByApartmentId(apartment.getId());
			apartment.setBusyDates(getBusyDatesFromReservations(reservations));
		}
	}
	public static void populateCommentsList() {
		List<CommentDbModel> dbModels = DatabaseController.getCommentsFromDatabase();
		comments = getCommentsFromDbModels(dbModels);
	}
	public static void populateApartmentPictureReferenceList() {
		//apartmentPictureReferencePairings = new HashMap<Apartment, ArrayList<String>>(); 
		List<ApartmentPairingDbModel> dbModels = DatabaseController.getApartmentPairingsFromDatabase("apartmentPictureReferencePairings.txt");
		for(ApartmentPairingDbModel dbm : dbModels) {
			Apartment apartment = findApartmentById(Integer.parseInt(dbm.apartmentId));
			/*
			if(!apartmentPictureReferencePairings.containsKey(apartment))
				apartmentPictureReferencePairings.put(apartment, new ArrayList<String>());
			apartmentPictureReferencePairings.get(apartment).add(dbm.strPair);
			*/
			apartment.getPictures().add(dbm.strPair);
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
	public static void saveApartmentAmenitiyPairingList() {
		List<ApartmentPairingDbModel> dbModels = getApartmentAmenityDbModelsFromApartments();
		DatabaseController.saveApartmentAmenityPairingsToDatabase(dbModels, "apartmentAmenityPairings.txt");
	}
	public static void saveAmenityList() {
		List<AmenityDbModel> dbModels = getDbModelsFromAmenities();
		DatabaseController.saveAmenitiesToDatabase(dbModels);	
	}
	public static void saveReservationsList() {
		List<ReservationDbModel> dbModels = getDbModelsFromReservations();
		DatabaseController.saveReservationsToDatabase(dbModels);
	}
	public static void saveCommentList() {
		List<CommentDbModel> dbModels = getDbModelsFromComments();
		DatabaseController.saveCommentsToDatabase(dbModels);
	}
	public static void saveApartmentPicturePairingList() {
		List<ApartmentPairingDbModel> dbModels = getApartmentPictureDbModelsFromApartments();
		DatabaseController.saveApartmentAmenityPairingsToDatabase(dbModels, "apartmentPictureReferencePairings.txt");
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
	private static ArrayList<Reservation> getReservationsFromDbModels(List<ReservationDbModel> dbModels) {
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		for(ReservationDbModel dbm : dbModels) {
			list.add(createReservationFromModel(dbm));
		}
		return list;
	}
	private static ArrayList<Comment> getCommentsFromDbModels(List<CommentDbModel> dbModels) {
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(CommentDbModel dbm : dbModels) {
			list.add(createCommentFromModel(dbm));
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
			dbm.postNumber,
			Boolean.parseBoolean(dbm.enabled)
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
			ApartmentStatus.valueOf(dbm.status),
			Boolean.parseBoolean(dbm.enabled)
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
	private static Reservation createReservationFromModel(ReservationDbModel dbm) {
		return new Reservation(
				Integer.parseInt(dbm.id),
				findApartmentById(Integer.parseInt(dbm.apartmentId)),
				dbm.date,
				Integer.parseInt(dbm.numberOfNights),
				dbm.totalPrice,
				dbm.reservationMessage,
				(Guest) findUserById(Integer.parseInt(dbm.guestId)),
				ReservationStatus.valueOf(dbm.status));
	}
	private static Comment createCommentFromModel(CommentDbModel dbm) {
		return new Comment(
			Integer.parseInt(dbm.id),
			(Guest) findUserById(Integer.parseInt(dbm.guestId)),
			findApartmentById(Integer.parseInt(dbm.apartmentId)),
			dbm.message,
			Double.parseDouble(dbm.rating),
			Boolean.parseBoolean(dbm.hidden)
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
	private static List<ApartmentPairingDbModel> getApartmentAmenityDbModelsFromApartments() {
		ArrayList<ApartmentPairingDbModel> dbModels = new ArrayList<ApartmentPairingDbModel>();
		Integer id = 1;
		for(Apartment apartment : apartments) {
			for(Amenity amenity : apartment.getAmenities()) {
				ApartmentPairingDbModel dbm = createModelFromApartmentAndAmenity(id, apartment.getId(), amenity.getId());
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
	private static List<ReservationDbModel> getDbModelsFromReservations() {
		ArrayList<ReservationDbModel> dbModels = new ArrayList<ReservationDbModel>();
		for(Reservation r : reservations) {
			ReservationDbModel dbm = createModelFromReservation(r);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static List<CommentDbModel> getDbModelsFromComments() {
		ArrayList<CommentDbModel> dbModels = new ArrayList<CommentDbModel>();
		for(Comment c : comments) {
			CommentDbModel dbm = createModelFromComment(c);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static List<ApartmentPairingDbModel> getApartmentPictureDbModelsFromApartments() {
		List<ApartmentPairingDbModel> dbModels = new ArrayList<ApartmentPairingDbModel>();
		Integer id = 1;
		for(Apartment apartment : apartments) {
			String apartmentId = apartment.getId().toString();
			for(String picture : apartment.getPictures()) {
				dbModels.add(new ApartmentPairingDbModel(id.toString(), apartmentId, picture));
				id = new Integer(id.intValue() + 1);
			}
		}
		/*
	    Iterator<Entry<Apartment, ArrayList<String>>> it = apartmentPictureReferencePairings.entrySet().iterator();
	    while (it.hasNext()) {
			Map.Entry<Apartment, ArrayList<String>> pair = (Map.Entry<Apartment, ArrayList<String>>)it.next();
	        String apartmentId = ((Apartment) pair.getKey()).getId().toString();
			ArrayList<String> picRefList = (ArrayList<String>) pair.getValue();
			for(String picRef : picRefList) {
				list.add(new ApartmentPairingDbModel(id.toString(), apartmentId, picRef));
				id = new Integer(id.intValue() + 1);
			}
				
	        it.remove();
	    }
	    */
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
			l.getPostNumber(),
			l.getEnabled().toString()
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
			a.getStatus().toString(),
			a.getEnabled().toString());
	}
	public static ApartmentPairingDbModel createModelFromApartmentAndAmenity(
			Integer id, 
			Integer apartmentId,
			Integer amenityId) {
			return new ApartmentPairingDbModel(
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
	public static ReservationDbModel createModelFromReservation(Reservation r) {
		return new ReservationDbModel(
			r.getId().toString(),
			r.getApartment().getId().toString(),
			r.getDate(),
			r.getNumberOfNights().toString(),
			r.getTotalPrice().toString(),
			r.getReservationMessage(),
			r.getGuest().getId().toString(),
			r.getStatus().toString()
		);
	}
	public static CommentDbModel createModelFromComment(Comment c) {
		return new CommentDbModel(
			c.getId().toString(),
			c.getGuest().getId().toString(),
			c.getApartment().getId().toString(),
			c.getMessage(),
			c.getRating().toString(),
			c.getHidden().toString()
		);
	}
	
	//Create tableModel from containerModel
	public static ApartmentTableModel createTableModelFromApartment(Apartment a) {
		return new ApartmentTableModel(
			a.getId().toString(),
			a.getType().toString(),
			a.getRoomCount().toString(),
			a.getGuestCount().toString(),
			//DatabaseController.getLocationAsString(a.getLocation()),
			a.getLocation().getCity(),
			a.getHost().getUsername(),
			a.getPrice().toString(),
			a.getCheckInTime(),
			a.getCheckOutTime(),
			a.getStatus().toString(),
			combineAmenitiesIntoOneString(a.getAmenities()));
	}
	private static String combineAmenitiesIntoOneString(ArrayList<Amenity> amenities) {
		String result = "";
		for(Amenity amenity : amenities) {
			result = result + amenity.getName() + ", ";
		}
		if(result.length() > 1)
			return result.substring(0, result.length() - 2);
		else
			return result;
	}
	public static AmenityTableModel createTableModelFromApartmentAndAmenity(Amenity a, String checked) {
		return new AmenityTableModel(
		a.getId().toString(),
		a.getName(),
		a.getDetails(),
		a.getEnabled().toString(),
		checked);
	}	
	public static ReservationTableModel createTableModelFromReservation(Reservation r) {
		return new ReservationTableModel(
			r.getId().toString(),
			DatabaseController.getLocationAsString(r.getApartment().getLocation()),
			r.getDate(),
			r.getNumberOfNights().toString(),
			r.getTotalPrice(),
			r.getReservationMessage(),
			r.getGuest().getFirstName() + " " + r.getGuest().getLastName(),
			r.getStatus().toString(),
			reservationNightsHavePassed(r)
		);
	}
	public static CommentTableModel createTableModelFromComment(Comment c) {
		return new CommentTableModel(
			c.getId().toString(),
			c.getGuest().getFirstName() + " " + c.getGuest().getLastName(),
			c.getMessage(),
			c.getRating().toString(),
			c.getHidden().toString()
		);
	}
	
	//Reservation Nights Have Passed
	private static String reservationNightsHavePassed(Reservation r) {
		try {
			Date currentDate = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(r.getDate()));
			c.add(Calendar.DATE, r.getNumberOfNights());
			Date finishDate = sdf.parse(sdf.format(c.getTime()));
			if(finishDate.compareTo(currentDate) < 0) {
				return "true";
			} else {
				return "false";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "null";
		}
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
	
	//Find or Filter User/Users
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
	public static ArrayList<User> filterUsersFromSearchOptions(
			ArrayList<User> userList, String role, String username, String firstName, String lastName, String gender) {
		return new ArrayList<>(
			userList.stream()
				.filter(
					user ->
					(role.equals("NONE")) ? true : user.getRole().toString().equals(role))
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
	public static User findUserFromSearchOptions(
			ArrayList<User> userList, String role, String username, String firstName, String lastName, String gender) {
		
		if(role.equals("NONE")
		&& username.isEmpty()
		&& firstName.isEmpty()
		&& lastName.isEmpty()
		&& gender.equals("NONE"))
			return null;
		
		return userList.stream()
				.filter(
					user -> 
						(role.equals("NONE")) ? true : user.getRole().toString().equals(role))
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
				.findFirst()
				.orElse(null);
	}


	//Find or Filter Apartment/Apartments
	public static Apartment findApartmentById(Integer id) {
		return apartments.stream()
			.filter(
				apartment -> 
					id.equals(apartment.getId()))
			.findFirst()
			.orElse(null);
	}
	public static ArrayList<Apartment> findApartmentsByStatusAndEnabled(String status, Boolean enabled) {
		return new ArrayList<>(
			apartments.stream()
				.filter(
					apartment -> 
						status.equals(apartment.getStatus().toString()))
				.filter(
						apartment -> 
							enabled.equals(apartment.getEnabled()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Apartment> filterApartmentsFromSearchOptions(
		ArrayList<Apartment> apartmentList,
		ArrayList<Amenity> amenityList,
		ApartmentStatus status,
		String datepickerArrive, 
		String datepickerLeave,
		String timeArrive,
		String timeLeave,
		String priceMin, 
		String priceMax, 
		String roomCountMin, 
		String roomCountMax, 
		String guestCount, 
		String location) {
		return new ArrayList<>(
			apartmentList.stream()
			.filter(
					apartment -> 
						apartment.getStatus() == status)
			.filter(
					apartment -> 
						amenityList.isEmpty() || apartmentHasAmenities(apartment, amenityList))
			.filter(
				apartment ->
					datepickerArrive.isEmpty() || apartmentReservationStartAtDate(apartment, datepickerArrive))
			.filter(
				apartment ->
					datepickerLeave.isEmpty() || apartmentReservationEndAtDate(apartment, datepickerLeave))
			.filter(
				apartment ->
					timeArrive.isEmpty() || apartment.getCheckInTime().equals(timeArrive))
			.filter(
				apartment ->
					timeLeave.isEmpty() || apartment.getCheckInTime().equals(timeLeave))
			.filter(
				apartment ->
					(priceMin.isEmpty() && priceMax.isEmpty())
					|| (priceMin.isEmpty() && PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.LESS_THAN_OR_EQUAL_TO, priceMax))
					|| (priceMax.isEmpty() && PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.GREATER_THAN_OR_EQUAL_TO, priceMin))
					|| (PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.LESS_THAN_OR_EQUAL_TO, priceMax)
						&& PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.GREATER_THAN_OR_EQUAL_TO, priceMin)))
			.filter(
				apartment ->
					(roomCountMin.isEmpty() && roomCountMax.isEmpty())
					|| (roomCountMin.isEmpty() && (apartment.getRoomCount() <= getNumFromString(roomCountMax)))
					|| (roomCountMax.isEmpty() && (apartment.getRoomCount() >= getNumFromString(roomCountMin)))
					|| ((apartment.getRoomCount() <= getNumFromString(roomCountMax))
						&& (apartment.getRoomCount() >= getNumFromString(roomCountMin))))
			.filter(
				apartment ->
					guestCount.isEmpty() || apartment.getGuestCount() == getNumFromString(guestCount))
			.filter(
				apartment ->
					location.isEmpty() || apartment.getLocation().getCity().contains(location))
			.collect(Collectors.toList())
		);
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
	public static ArrayList<Apartment> findApartmentsByStatusAndHostIdAndEnabled(ApartmentStatus status, Integer id, Boolean enabled) {
		return new ArrayList<>(
			apartments.stream()
				.filter(
					apartment -> 
						apartment.getStatus() == status)
				.filter(
						apartment -> 
							id.equals(apartment.getHost().getId()))
				.filter(
						apartment -> 
							enabled.equals(apartment.getEnabled()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Apartment> findApartmentsByEnabled(Boolean enabled) {
		return new ArrayList<>(
			apartments.stream()
				.filter(
						apartment -> 
							enabled.equals(apartment.getEnabled()))
				.collect(Collectors.toList())
		);
	}
	public static Apartment findApartmentFromSearchOptions(
			ArrayList<Apartment> apartmentList,
			ArrayList<Amenity> amenityList,
			ApartmentStatus status,
			String datepickerArrive, 
			String datepickerLeave,
			String timeArrive,
			String timeLeave,
			String priceMin, 
			String priceMax, 
			String roomCountMin, 
			String roomCountMax, 
			String guestCount, 
			String location) {
		
			if(amenityList.isEmpty()
			&& datepickerArrive.isEmpty()
			&& datepickerLeave.isEmpty()
			&& timeArrive.isEmpty()
			&& timeLeave.isEmpty()
			&& priceMin.isEmpty()
			&& priceMax.isEmpty()
			&& roomCountMin.isEmpty()
			&& guestCount.isEmpty()
			&& location.isEmpty())
				return null;
				
			return apartmentList.stream()
				.filter(
						apartment -> 
							apartment.getStatus() == status)
				.filter(
					apartment -> 
						amenityList.isEmpty() || apartmentHasAmenities(apartment, amenityList))
				.filter(
					apartment ->
						datepickerArrive.isEmpty() || apartmentReservationStartAtDate(apartment, datepickerArrive))
				.filter(
					apartment ->
						datepickerLeave.isEmpty() || apartmentReservationEndAtDate(apartment, datepickerLeave))
				.filter(
					apartment ->
						timeArrive.isEmpty() || apartment.getCheckInTime().equals(timeArrive))
				.filter(
					apartment ->
						timeLeave.isEmpty() || apartment.getCheckInTime().equals(timeLeave))
				.filter(
					apartment ->
						(priceMin.isEmpty() && priceMax.isEmpty())
						|| (priceMin.isEmpty() && PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.LESS_THAN_OR_EQUAL_TO, priceMax))
						|| (priceMax.isEmpty() && PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.GREATER_THAN_OR_EQUAL_TO, priceMin))
						|| (PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.LESS_THAN_OR_EQUAL_TO, priceMax)
							&& PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.GREATER_THAN_OR_EQUAL_TO, priceMin)))
				.filter(
					apartment ->
						(roomCountMin.isEmpty() && roomCountMax.isEmpty())
						|| (roomCountMin.isEmpty() && (apartment.getRoomCount() <= getNumFromString(roomCountMax)))
						|| (roomCountMax.isEmpty() && (apartment.getRoomCount() >= getNumFromString(roomCountMin)))
						|| ((apartment.getRoomCount() <= getNumFromString(roomCountMax))
							&& (apartment.getRoomCount() >= getNumFromString(roomCountMin))))
				.filter(
					apartment ->
						guestCount.isEmpty() || apartment.getGuestCount() == getNumFromString(guestCount))
				.filter(
					apartment ->
						location.isEmpty() || apartment.getLocation().getCity().contains(location))
				.findFirst()
				.orElse(null);
	/*
			return new ArrayList<>(
					apartmentList.stream()
					.filter(
						apartment -> 
							type.equals("NONE") || apartment.getType().toString().contains(type))
					.filter(
							apartment -> 
								roomCount.isEmpty() || apartment.getRoomCount() == getNumFromString(roomCount))
					.filter(
							apartment -> 
								guestCount.isEmpty() || apartment.getGuestCount() == getNumFromString(guestCount))
					.filter(
							apartment ->
								price.isEmpty()
								|| PriceManager.comparePrices(apartment.getPrice(), ComparisonOption.LESS_THAN_OR_EQUAL_TO, price))
					.filter(
							apartment -> 
								enabled.equals(apartment.getEnabled()))
					.collect(Collectors.toList())
			);
	*/
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
	public static Amenity findChosenAmenityById(ArrayList<Amenity> chosenAmenities, Integer id) {
		return chosenAmenities.stream()
			.filter(
				amenity -> 
					id.equals(amenity.getId()))
			.findFirst()
			.orElse(null);
	}
	
	//Find or Filter Reservation/Reservations
	public static Reservation findReservationById(Integer id) {
		return reservations.stream()
			.filter(
				reservation -> 
					id.equals(reservation.getId()))
			.findFirst()
			.orElse(null);
	}
	public static ArrayList<Reservation> findReservationsByApartmentId(Integer id) {
		return new ArrayList<>(
			reservations.stream()
				.filter(
					reservation -> 
						id.equals(reservation.getApartment().getId()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Reservation> findReservationsByGuestId(Integer id) {
		return new ArrayList<>(
			reservations.stream()
				.filter(
					reservation -> 
						id.equals(reservation.getGuest().getId()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Reservation> findReservationsByHostId(Integer id) {
		return new ArrayList<>(
			reservations.stream()
				.filter(
					reservation -> 
						id.equals(reservation.getApartment().getHost().getId()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Reservation> findReservationsByApartmentIdAndGuestId(Integer apartmentId, Integer userId) {
		return new ArrayList<>(
				reservations.stream()
					.filter(
						reservation -> 
							apartmentId.equals(reservation.getApartment().getId()))
					.filter(
							reservation -> 
								userId.equals(reservation.getGuest().getId()))
					.collect(Collectors.toList())
			);
	}
	public static ArrayList<Reservation> filterReservationsFromSearchOptions(ArrayList<Reservation> reservations,
			String status,
			String username) {
		return new ArrayList<>(
			reservations.stream()
				.filter(
					reservation ->
						(status.equals("NONE")) ? true : reservation.getStatus().toString().equals(status))
				.filter(
					reservation ->
						username.isEmpty() || reservation.getGuest().getUsername().contains(username))
				.collect(Collectors.toList())
		);
	}
	public static Reservation findReservationFromSearchOptions(ArrayList<Reservation> reservations,
			String status,
			String username) {
		if(status.equals("NONE")
		&& username.isEmpty())
			return null;
		
		return reservations.stream()
				.filter(
					reservation ->
						(status.equals("NONE")) ? true : reservation.getStatus().toString().equals(status))
				.filter(
					reservation ->
						username.isEmpty() || reservation.getGuest().getUsername().contains(username))
				.findFirst()
				.orElse(null);
	}

	//Find Comment/Comments
	public static Comment findCommentById(Integer id) {
		return comments.stream()
			.filter(
				comment -> 
					id.equals(comment.getId()))
			.findFirst()
			.orElse(null);
	}
	public static ArrayList<Comment> findCommentsByApartmentIdAndHidden(Integer id, Boolean hidden) {
		return new ArrayList<>(
			comments.stream()
				.filter(
					comment -> 
						id.equals(comment.getApartment().getId()))
				.filter(
					comment ->
						hidden.equals(comment.getHidden()))
				.collect(Collectors.toList())
		);
	}
	public static ArrayList<Comment> findCommentsByApartmentId(Integer id) {
		return new ArrayList<>(
			comments.stream()
				.filter(
					comment -> 
						id.equals(comment.getApartment().getId()))
				.collect(Collectors.toList())
		);
	}
	
	//Sort List
	public static ArrayList<ApartmentTableModel> sortTableApartmentList(ArrayList<ApartmentTableModel> apartmentList, ApartmentTableParameter parameter, Boolean reversed) {
		switch(parameter) {
			case TYPE:
				return sortApartmentsByString(apartmentList, ApartmentTableModel::getType, reversed);
			case ROOM_COUNT:
				return sortApartmentsByRoomCount(apartmentList, reversed);
			case GUEST_COUNT:
				return sortApartmentsByGuestCount(apartmentList, reversed);
			case LOCATION:
				return sortApartmentsByString(apartmentList, ApartmentTableModel::getLocation, reversed);
			case HOST:
				return sortApartmentsByString(apartmentList, ApartmentTableModel::getHost, reversed);
			case PRICE:
				return sortApartmentsByPrice(apartmentList, reversed);
			case BOOKING_TIME:
				return sortApartmentsByBookingTime(apartmentList, reversed);
			case CANCEL_TIME:
				return sortApartmentsByCancelTime(apartmentList, reversed);
			case STATUS:
				return sortApartmentsByString(apartmentList, ApartmentTableModel::getStatus, reversed);
			default:
		}
		return null;
	}
	private static ArrayList<ApartmentTableModel> sortApartmentsByString(
		ArrayList<ApartmentTableModel> apartmentList, 
		Function<ApartmentTableModel, String> sortKey, Boolean reversed) {
		if(!reversed)
			return new ArrayList<ApartmentTableModel>(
				apartmentList.stream()
					.sorted(Comparator.comparing(sortKey))
					.collect(Collectors.toList())
			);
		else
			return new ArrayList<ApartmentTableModel>(
				apartmentList.stream()
					.sorted(Comparator.comparing(sortKey).reversed())
					.collect(Collectors.toList())
			);
	}
	private static ArrayList<ApartmentTableModel> sortApartmentsByRoomCount(
			ArrayList<ApartmentTableModel> apartmentList, 
			Boolean reversed) {
			if(!reversed)
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingInt(ApartmentTableModel::getRoomCountAsInt))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingInt(ApartmentTableModel::getRoomCountAsInt).reversed())
						.collect(Collectors.toList())
				);
		}
	private static ArrayList<ApartmentTableModel> sortApartmentsByGuestCount(
			ArrayList<ApartmentTableModel> apartmentList, 
			Boolean reversed) {
			if(!reversed)
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingInt(ApartmentTableModel::getGuestCountAsInt))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingInt(ApartmentTableModel::getGuestCountAsInt).reversed())
						.collect(Collectors.toList())
				);
		}
	private static ArrayList<ApartmentTableModel> sortApartmentsByPrice(
			ArrayList<ApartmentTableModel> apartmentList, 
			Boolean reversed) {
			if(!reversed)
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getPriceAsDouble))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getPriceAsDouble).reversed())
						.collect(Collectors.toList())
				);
		}
	private static ArrayList<ApartmentTableModel> sortApartmentsByBookingTime(
			ArrayList<ApartmentTableModel> apartmentList, 
			Boolean reversed) {
			if(!reversed)
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getBookingTimeAsDouble))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getBookingTimeAsDouble).reversed())
						.collect(Collectors.toList())
				);
		}
	private static ArrayList<ApartmentTableModel> sortApartmentsByCancelTime(
			ArrayList<ApartmentTableModel> apartmentList, 
			Boolean reversed) {
			if(!reversed)
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getCancelTimeAsDouble))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ApartmentTableModel>(
					apartmentList.stream()
						.sorted(Comparator.comparingDouble(ApartmentTableModel::getCancelTimeAsDouble).reversed())
						.collect(Collectors.toList())
				);
		}
	public static ArrayList<ReservationTableModel> sortTableReservationList(
			ArrayList<ReservationTableModel> reservationList, ReservationTableParameter parameter,
			Boolean reversed) {
		switch(parameter) {
			case APARTMENT:
				return sortReservationsByString(reservationList, ReservationTableModel::getApartment, reversed);
			case DATE:
				return sortReservationsByDate(reservationList, reversed);
			case NIGHTS:
				return sortReservationsByNights(reservationList, reversed);
			case GUEST:
				return sortReservationsByString(reservationList, ReservationTableModel::getGuest, reversed);
			case MESSAGE:
				return sortReservationsByString(reservationList, ReservationTableModel::getMessage, reversed);
			case PRICE:
				return sortReservationsByPrice(reservationList, reversed);
			case STATUS:
				return sortReservationsByString(reservationList, ReservationTableModel::getStatus, reversed);
			default:
		}
		return null;
	}
	private static ArrayList<ReservationTableModel> sortReservationsByPrice(
			ArrayList<ReservationTableModel> reservationList, Boolean reversed) {
					if(!reversed)
				return new ArrayList<ReservationTableModel>(
					reservationList.stream()
						.sorted(Comparator.comparingDouble(ReservationTableModel::getPriceAsDouble))
						.collect(Collectors.toList())
				);
			else
				return new ArrayList<ReservationTableModel>(
					reservationList.stream()
						.sorted(Comparator.comparingDouble(ReservationTableModel::getPriceAsDouble).reversed())
						.collect(Collectors.toList())
				);
	}
	private static ArrayList<ReservationTableModel> sortReservationsByNights(
			ArrayList<ReservationTableModel> reservationList, Boolean reversed) {
		if(!reversed)
			return new ArrayList<ReservationTableModel>(
				reservationList.stream()
					.sorted(Comparator.comparingInt(ReservationTableModel::getNightsAsInt))
					.collect(Collectors.toList())
			);
		else
			return new ArrayList<ReservationTableModel>(
				reservationList.stream()
					.sorted(Comparator.comparingInt(ReservationTableModel::getNightsAsInt).reversed())
					.collect(Collectors.toList())
			);
	}
	private static ArrayList<ReservationTableModel> sortReservationsByDate(
			ArrayList<ReservationTableModel> reservationList, Boolean reversed) {
		if(!reversed)
		return new ArrayList<ReservationTableModel>(
			reservationList.stream()
				.sorted(Comparator.comparing(ReservationTableModel::getDateAsYYYYMMDDString))
				.collect(Collectors.toList())
		);
		else
		return new ArrayList<ReservationTableModel>(
			reservationList.stream()
				.sorted(Comparator.comparing(ReservationTableModel::getDateAsYYYYMMDDString).reversed())
				.collect(Collectors.toList())
		);
	}
	private static ArrayList<ReservationTableModel> sortReservationsByString(
		ArrayList<ReservationTableModel> reservationList,
		Function<ReservationTableModel, String> sortKey, Boolean reversed) {
			if(!reversed)
			return new ArrayList<ReservationTableModel>(
				reservationList.stream()
					.sorted(Comparator.comparing(sortKey))
					.collect(Collectors.toList())
			);
		else
			return new ArrayList<ReservationTableModel>(
				reservationList.stream()
					.sorted(Comparator.comparing(sortKey).reversed())
					.collect(Collectors.toList())
			);
	}
	private static ArrayList<ReservationTableModel> sortReservations(ArrayList<ReservationTableModel> reservationList,
			Function<ReservationTableModel, String> sortKey, Boolean reversed) {
	if(!reversed)
		return new ArrayList<ReservationTableModel>(
			reservationList.stream()
				.sorted(Comparator.comparing(sortKey))
				.collect(Collectors.toList())
		);
	else
		return new ArrayList<ReservationTableModel>(
				reservationList.stream()
				.sorted(Comparator.comparing(sortKey).reversed())
				.collect(Collectors.toList())
		);
	}

/*	
	

	//Checks
	public static boolean isAdmin(String role) {
		return role.equals("ADMIN");
	}
	public static boolean isHost(String role) {
		return role.equals("HOST");
	}
*/
	public static boolean apartmentIsFreeAtDate(String dateStr, ArrayList<String> apartmentDates) {
			for(String busyDate: apartmentDates) {
				if(busyDate.equals(dateStr))
					return false;
			}
		return true;
	}
	public static boolean apartmentReservationStartAtDate(Apartment apartment, String date) {
		ArrayList<Reservation> reservations = findReservationsByApartmentId(apartment.getId());
		for(Reservation reservation : reservations) {
			if(date.equals(reservation.getDate()))
				return true;
		}
		return false;
	}
	public static boolean apartmentReservationEndAtDate(Apartment apartment, String date) {
		ArrayList<Reservation> reservations = findReservationsByApartmentId(apartment.getId());
		for(Reservation reservation : reservations) {
			if(date.equals(reservation.getEndDate()))
				return true;
		}
		return false;
	}
	private static boolean amenityAlreadyExists(Amenity chosenAmenity) {
		Amenity foundAmenity = amenities.stream()
		.filter(
				amenity -> 
					chosenAmenity.getName().equals(amenity.getName()))
		.filter(
				amenity -> 
					chosenAmenity.getDetails().equals(amenity.getDetails()))
		.filter(
				amenity -> 
					chosenAmenity.getEnabled().equals(amenity.getEnabled()))
		.findFirst()
		.orElse(null);	
		if(foundAmenity == null)
			return false;
		return true;
	}
	private static boolean locationAlreadyExists(Location chosenLocation) {
		Location foundLocation = locations.stream()
		.filter(
				location -> 
					chosenLocation.getLatitude().equals(location.getLatitude()))
		.filter(
				location -> 
					chosenLocation.getLongitude().equals(location.getLongitude()))
		.filter(
				location -> 
					chosenLocation.getStreetName().equals(location.getStreetName()))
		.filter(
				location -> 
					chosenLocation.getStreetNumber().equals(location.getStreetNumber()))
		.filter(
				location -> 
					chosenLocation.getCity().equals(location.getCity()))
		.filter(
				location -> 
					chosenLocation.getPostNumber().equals(location.getPostNumber()))
		.filter(
				location -> 
					chosenLocation.getEnabled().equals(location.getEnabled()))
		.findFirst()
		.orElse(null);	
		if(foundLocation == null)
			return false;
		return true;
	}
	private static boolean apartmentAlreadyExists(Apartment chosenApartment) {
		Apartment foundApartment = apartments.stream()
				.filter(
						apartment -> 
							chosenApartment.getType().equals(apartment.getType()))
				.filter(
						apartment -> 
							chosenApartment.getRoomCount().equals(apartment.getRoomCount()))
				.filter(
						apartment -> 
							chosenApartment.getGuestCount().equals(apartment.getGuestCount()))
				.filter(
						apartment -> 
							chosenApartment.getRoomCount().equals(apartment.getRoomCount()))
				.filter(
						apartment -> 
							chosenApartment.getLocation().getId().equals(apartment.getLocation().getId()))
				.filter(
						apartment -> 
							chosenApartment.getHost().getId().equals(apartment.getHost().getId()))
				.filter(
						apartment -> 
							chosenApartment.getPrice().equals(apartment.getPrice()))
				.filter(
						apartment -> 
							chosenApartment.getCheckInTime().equals(apartment.getCheckInTime()))
				.filter(
						apartment -> 
							chosenApartment.getCheckOutTime().equals(apartment.getCheckOutTime()))
				.filter(
						apartment -> 
							chosenApartment.getStatus().equals(apartment.getStatus()))
		.findFirst()
		.orElse(null);	
		if(foundApartment == null)
			return false;
		return true;
	}
	private static boolean commentAlreadyExists(Comment chosenComment) {
		Comment foundComment = 
				comments.stream()
					.filter(
						comment -> 
							chosenComment.getGuest().getId().equals(comment.getGuest().getId()))
					.filter(
						comment ->
							chosenComment.getApartment().getId().equals(comment.getApartment().getId()))
					.filter(
							comment ->
								chosenComment.getMessage().equals(comment.getMessage()))
					.filter(
							comment ->
								chosenComment.getRating().equals(comment.getRating()))
					.filter(
							comment ->
								chosenComment.getHidden().equals(comment.getHidden()))
					.findFirst()
					.orElse(null);	
		if(foundComment == null)
			return false;
		return true;
	}
	private static boolean reservationAlreadyExists(Reservation chosenReservation) {
		Reservation foundReservation = 
				reservations.stream()
					.filter(
						reservation -> 
							chosenReservation.getApartment().getId().equals(reservation.getApartment().getId()))
					.filter(
						reservation -> 
							chosenReservation.getDate().equals(reservation.getDate()))
					.filter(
						reservation -> 
							chosenReservation.getTotalPrice().equals(reservation.getTotalPrice()))
					.filter(
						reservation -> 
							chosenReservation.getReservationMessage().equals(reservation.getReservationMessage()))
					.filter(
						reservation -> 
							chosenReservation.getGuest().getId().equals(reservation.getGuest().getId()))
					.filter(
						reservation -> 
							chosenReservation.getStatus().equals(reservation.getStatus()))
					.findFirst()
					.orElse(null);	
		if(foundReservation == null)
			return false;
		return true;
	}
	private static boolean apartmentHasAmenities(Apartment apartment, ArrayList<Amenity> amenityList) {
		for(Amenity amenity : amenityList) {
			if(!apartment.getAmenities().contains(amenity))
				return false;
		}
		return true;
	}
	
	//Logical Delete
	public static void logicalDeleteAmenity(Amenity amenity) {
		amenity.setEnabled(false);
		for(Apartment apartment : apartments) {
			if(apartment.getAmenities().contains(amenity))
			apartment.getAmenities().remove(amenity);
		}
	}
	public static void logicalDeleteApartment(Apartment apartment) {
		apartment.setEnabled(false);
		apartment.getHost().getApartmentsUpForReservation().remove(apartment);
		apartment.setAmenities(new ArrayList<>());
	}
	public static void logicalDeleteLocation(Location location) {
		location.setEnabled(false);
	}

	//Get num from String
	private static Integer getNumFromString(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer format");
			return 0;
		}
	}

	//Get busy dates from reservations
	private static ArrayList<String> getBusyDatesFromReservations(ArrayList<Reservation> reservations) {
		ArrayList<String> dates = new ArrayList<>();
		for(Reservation reservation : reservations) {
			String reservationDate = reservation.getDate();
			int numberOfNights = reservation.getNumberOfNights();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(reservationDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < numberOfNights; i++) {
				dates.add(sdf.format(c.getTime()));
				c.add(Calendar.DATE, 1);
			}
		}
		return dates;
	}

	//Add entry
	public static boolean addAmenity(Amenity amenity) {
		if(amenityAlreadyExists(amenity))
			return false;
		amenities.add(amenity);
		return true;
	}
	public static boolean addLocationAndApartment(Location location, Apartment apartment) {
		if(locationAlreadyExists(location) && apartmentAlreadyExists(apartment))
			return false;
		locations.add(location);
		apartments.add(apartment);
		return true;
	}
	public static boolean addComment(Comment comment) {
		if(commentAlreadyExists(comment))
			return false;
		comments.add(comment);
		return true;
	}
	public static boolean addReservation(Reservation reservation) {
		if(reservationAlreadyExists(reservation))
			return false;
		reservations.add(reservation);
		return true;
	}
}
