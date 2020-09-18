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

import dbm.ApartmentDbModel;
import dbm.LocationDbModel;
import dbm.UserDbModel;
import listener.ContextListener;
import model.Admin;
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
	
	public static ArrayList<User> getUsersFromDatabase() {
		List<String> userLines = getLinesFromFile(getPathToFile("users.txt"));
		List<UserDbModel> userDbModels = getUserDbModelsFromLines(userLines);
		return getUsersFromDbModels(userDbModels);
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
	public static ArrayList<User> getUsersFromDbModels(List<UserDbModel> userDbModels) {
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
	public static Admin createAdminFromModel(UserDbModel dbm) {
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
	public static Host createHostFromModel(UserDbModel dbm) {
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
	public static Guest createGuestFromModel(UserDbModel dbm) {
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
	public static String mergeIntoLine(UserDbModel dbm) {
		String line = "";
		Field[] fields = dbm.getClass().getDeclaredFields();
		for(Field f : fields) {
			try {
				line = line + f.get(dbm).toString() + "|";
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
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
	public static ArrayList<Apartment> getApartmentsFromDatabase() {
		List<String> apartmentLines = getLinesFromFile(getPathToFile("apartments.txt"));
		List<ApartmentDbModel> apartmentDbModels = getApartmentDbModelsFromLines(apartmentLines);
		return getApartmentsFromDbModels(apartmentDbModels);
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
			Integer.parseInt(dbm.price),
			dbm.bookingTime,
			dbm.cancelTime,
			Status.valueOf(dbm.status)
		);
	}
	public static ArrayList<Location> getLocationsFromDatabase() {
		List<String> locationLines = getLinesFromFile(getPathToFile("locations.txt"));
		List<LocationDbModel> locationDbModels = getLocationDbModelsFromLines(locationLines);
		return getLocationsFromDbModels(locationDbModels);
	}
	private static ArrayList<Location> getLocationsFromDbModels(List<LocationDbModel> locationDbModels) {
		ArrayList<Location> list = new ArrayList<Location>();
		for(LocationDbModel dbm : locationDbModels) {
			list.add(createLocationFromModel(dbm));
		}
		return list;
	}
	private static Location createLocationFromModel(LocationDbModel dbm) {
		return new Location(
			Integer.parseInt(dbm.id),
			Float.parseFloat(dbm.geoWidth),
			Float.parseFloat(dbm.geoLength),
			dbm.streetName,
			dbm.streetNumber,
			dbm.city,
			dbm.postNumber
		);
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
			a.getBookingTime(),
			a.getCancelTime(),
			a.getPrice().toString());
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
			a.getBookingTime(),
			a.getCancelTime(),
			a.getPrice().toString());
	}
	
	public static String getLocationAsString(Location l) {
		return l.getStreetName() + " " 
			+ l.getStreetNumber() + ", " 
			+ l.getPostNumber() + " " 
			+ l.getCity() + "(" 
			+ l.getGeoWidth() + ", " 
			+ l.getGeoWidth() + ")";
	}
}
