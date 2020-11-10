package controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import dbm.AmenityDbModel;
import dbm.ApartmentAmenityDbModel;
import dbm.ApartmentDbModel;
import dbm.LocationDbModel;
import dbm.UserDbModel;
import listener.ContextListener;
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
import rm.ApartmentRequestModel;

public class DatabaseController {
	
	public static final String PATH_TO_RESOURCES = "WEBProjRA502013/WebContent/WEB-INF/resources/";
	public static ServletContext servletContext = ContextListener.context;
	
	public static List<UserDbModel> getUsersFromDatabase() {
		List<String> userLines = getLinesFromFile(getPathToFile("users.txt"));
		return getUserDbModelsFromLines(userLines);
	}
	public static void saveUsersToDatabase() {
		List<UserDbModel> userDbModels = getDbModelsFromUsers();
		List<String> lines = getLinesFromUserDbModels(userDbModels);
		saveLinesToFile(lines, getPathToFile("users.txt"));
	}

	public static List<String> getLinesFromFile(String file) { 
		List<String> lines = Collections.emptyList(); 
		try {
			lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
		return lines; 
	}
	public static String getPathToFile(String s) {
		String realPath = servletContext.getRealPath(".");
		String cutPath = realPath.split(".metadata")[0];
		return cutPath + PATH_TO_RESOURCES + s;
	}
	public static List<UserDbModel> getUserDbModelsFromLines(List<String> userLines) {
		ArrayList<UserDbModel> dbModels = new ArrayList<UserDbModel>();
		for(String line : userLines)
			dbModels.add(new UserDbModel(splitLine(line)));
		return dbModels;
	}
	public static String[] splitLine(String line) {
		return line.split("\\|");
	}
	public static List<UserDbModel> getDbModelsFromUsers() {
		ArrayList<UserDbModel> dbModels = new ArrayList<UserDbModel>();
		for(User u : ContainerController.users) {
			UserDbModel dbm = createModelFromUser(u);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	public static UserDbModel createModelFromUser(User user) {
		return new UserDbModel(
			user.getId().toString(),
			user.getUsername(),
			user.getPassword(),
			user.getFirstName(),
			user.getLastName(),
			user.getGender().toString(),
			user.getRole().toString(),
			user.getEnabled().toString()
		);
	}
	public static ArrayList<String> getLinesFromUserDbModels(List<UserDbModel> userDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(UserDbModel dbm : userDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	public static String mergeIntoLine(Object dbm) {
		String line = "";
		Field[] fields = dbm.getClass().getDeclaredFields();
		for(Field f : fields) {
			try {
				line = line + f.get(dbm).toString() + "|";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return line.substring(0, line.length() - 1);
	}
	public static void saveLinesToFile(List<String> lines, String file) {
		try {
			Files.write(Paths.get(file), lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}
	public static List<ApartmentDbModel> getApartmentsFromDatabase() {
		List<String> apartmentLines = getLinesFromFile(getPathToFile("apartments.txt"));
		return getApartmentDbModelsFromLines(apartmentLines);
	}
	public static List<ApartmentDbModel> getApartmentDbModelsFromLines(List<String> apartmentLines) {
		ArrayList<ApartmentDbModel> dbModels = new ArrayList<ApartmentDbModel>();
		for(String line : apartmentLines)
			dbModels.add(new ApartmentDbModel(splitLine(line)));
		return dbModels;
	}
	public static ArrayList<Apartment> getApartmentsFromDbModels(List<ApartmentDbModel> apartmentDbModels) {
		ArrayList<Apartment> list = new ArrayList<Apartment>();
		for(ApartmentDbModel dbm : apartmentDbModels) {
			list.add(createApartmentFromModel(dbm));
		}
		return list;
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
	public static List<LocationDbModel> getLocationsFromDatabase() {
		List<String> locationLines = getLinesFromFile(getPathToFile("locations.txt"));
		return getLocationDbModelsFromLines(locationLines);
	}
	private static List<LocationDbModel> getLocationDbModelsFromLines(List<String> locationLines) {
		ArrayList<LocationDbModel> dbModels = new ArrayList<LocationDbModel>();
		for(String line : locationLines)
			dbModels.add(new LocationDbModel(splitLine(line)));
		return dbModels;
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
	public static ApartmentRequestModel createRequestModelFromApartment(Apartment a) {
		return new ApartmentRequestModel(
			a.getId().toString(),
			a.getType().toString(),
			a.getRoomCount().toString(),
			a.getGuestCount().toString(),
			getLocationAsString(a.getLocation()),
			a.getHost().getUsername(),
			a.getPrice().toString(),
			a.getCheckInTime(),
			a.getCheckOutTime(),
			a.getPrice().toString());
	}
	
	public static String getLocationAsString(Location l) {
		return l.getStreetName() + " " 
			+ l.getStreetNumber() + ", " 
			+ l.getPostNumber() + " " 
			+ l.getCity() + "(" 
			+ l.getLatitude() + ", " 
			+ l.getLongitude() + ")";
	}
	
	public static List<AmenityDbModel> getAmenitiesFromDatabase() {
		List<String> amenityLines = getLinesFromFile(getPathToFile("amenities.txt"));
		return getAmenityDbModelsFromLines(amenityLines);
	}
	
	private static List<AmenityDbModel> getAmenityDbModelsFromLines(List<String> amenityLines) {
		ArrayList<AmenityDbModel> dbModels = new ArrayList<AmenityDbModel>();
		for(String line : amenityLines)
			dbModels.add(new AmenityDbModel(splitLine(line)));
		return dbModels;
	}
	public static AmenityDbModel createModelFromAmenity(Amenity a) {
		return new AmenityDbModel(
				a.getId().toString(),
				a.getName(),
				a.getDetails()
			);
	}
	public static void saveApartmentsToDatabase() {
		List<ApartmentDbModel> apartmentDbModels = getApartmentDbModelsFromApartments();
		List<String> lines = getLinesFromApartmentDbModels(apartmentDbModels);
		saveLinesToFile(lines, getPathToFile("apartments.txt"));
	}
	private static List<String> getLinesFromApartmentDbModels(List<ApartmentDbModel> apartmentDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ApartmentDbModel dbm : apartmentDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<ApartmentDbModel> getApartmentDbModelsFromApartments() {
		ArrayList<ApartmentDbModel> dbModels = new ArrayList<ApartmentDbModel>();
		for(Apartment a : ContainerController.apartments) {
			ApartmentDbModel dbm = createModelFromApartment(a);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	public static void saveLocationsToDatabase() {
		List<LocationDbModel> locationDbModels = getDbModelsFromLocations();
		List<String> lines = getLinesFromLocationDbModels(locationDbModels);
		saveLinesToFile(lines, getPathToFile("locations.txt"));
	}
	private static List<String> getLinesFromLocationDbModels(List<LocationDbModel> locationDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(LocationDbModel dbm : locationDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<LocationDbModel> getDbModelsFromLocations() {
		ArrayList<LocationDbModel> dbModels = new ArrayList<LocationDbModel>();
		for(Location l : ContainerController.locations) {
			LocationDbModel dbm = createModelFromLocation(l);
			dbModels.add(dbm);
		}
		return dbModels;
	}
	private static LocationDbModel createModelFromLocation(Location l) {
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
	public static void saveApartmentAmenityPairingsToDatabase() {
		List<ApartmentAmenityDbModel> dbModels = getApartmentAmenityDbModelsFromApartments();
		List<String> lines = getLinesFromApartmentAmenityDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("apartmentAmenityPairings.txt"));
	}
	private static List<String> getLinesFromApartmentAmenityDbModels(List<ApartmentAmenityDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ApartmentAmenityDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<ApartmentAmenityDbModel> getApartmentAmenityDbModelsFromApartments() {
		ArrayList<ApartmentAmenityDbModel> dbModels = new ArrayList<ApartmentAmenityDbModel>();
		Integer id = 1;
		for(Apartment apartment : ContainerController.apartments) {
			for(Amenity amenity : apartment.getAmenities()) {
				ApartmentAmenityDbModel dbm = createModelFromApartmentAndAmenity(id, apartment.getId(), amenity.getId());
				dbModels.add(dbm);
				id = new Integer(id.intValue() + 1);
			}
		}
		return dbModels;
	}
	private static ApartmentAmenityDbModel createModelFromApartmentAndAmenity(
		Integer id, 
		Integer apartmentId,
		Integer amenityId) {
		return new ApartmentAmenityDbModel(
			id.toString(), 
			apartmentId.toString(), 
			amenityId.toString()
		);
	}
	public static List<ApartmentAmenityDbModel> getApartmentAmenityPairingsFromDatabase() {
		List<String> lines = getLinesFromFile(getPathToFile("ApartmentAmenityPairings.txt"));
		return getApartmentAmenityDbModelsFromLines(lines);
	}
	private static List<ApartmentAmenityDbModel> getApartmentAmenityDbModelsFromLines(List<String> lines) {
		ArrayList<ApartmentAmenityDbModel> dbModels = new ArrayList<ApartmentAmenityDbModel>();
		for(String line : lines)
			dbModels.add(new ApartmentAmenityDbModel(splitLine(line)));
		return dbModels;
	}
}
