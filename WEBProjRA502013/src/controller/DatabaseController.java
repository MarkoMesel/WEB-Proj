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
	public static List<ApartmentDbModel> getApartmentDbModelsFromLines(List<String> apartmentLines) {
		ArrayList<ApartmentDbModel> dbModels = new ArrayList<ApartmentDbModel>();
		for(String line : apartmentLines)
			dbModels.add(new ApartmentDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<LocationDbModel> getLocationDbModelsFromLines(List<String> locationLines) {
		ArrayList<LocationDbModel> dbModels = new ArrayList<LocationDbModel>();
		for(String line : locationLines)
			dbModels.add(new LocationDbModel(splitLine(line)));
		return dbModels;
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
				a.getDetails(),
				a.getEnabled().toString()
			);
	}
	private static List<ApartmentAmenityDbModel> getApartmentAmenityDbModelsFromLines(List<String> lines) {
		ArrayList<ApartmentAmenityDbModel> dbModels = new ArrayList<ApartmentAmenityDbModel>();
		for(String line : lines)
			dbModels.add(new ApartmentAmenityDbModel(splitLine(line)));
		return dbModels;
	}
	
	//Get from database
	public static List<LocationDbModel> getLocationsFromDatabase() {
		List<String> locationLines = getLinesFromFile(getPathToFile("locations.txt"));
		return getLocationDbModelsFromLines(locationLines);
	}
	public static List<UserDbModel> getUsersFromDatabase() {
		List<String> userLines = getLinesFromFile(getPathToFile("users.txt"));
		return getUserDbModelsFromLines(userLines);
	}
	public static List<ApartmentDbModel> getApartmentsFromDatabase() {
		List<String> apartmentLines = getLinesFromFile(getPathToFile("apartments.txt"));
		return getApartmentDbModelsFromLines(apartmentLines);
	}
	public static List<AmenityDbModel> getAmenitiesFromDatabase() {
		List<String> amenityLines = getLinesFromFile(getPathToFile("amenities.txt"));
		return getAmenityDbModelsFromLines(amenityLines);
	}
	public static List<ApartmentAmenityDbModel> getApartmentAmenityPairingsFromDatabase() {
		List<String> lines = getLinesFromFile(getPathToFile("apartmentAmenityPairings.txt"));
		return getApartmentAmenityDbModelsFromLines(lines);
	}
	
	//Save to database
	public static void saveLocationsToDatabase(List<LocationDbModel> dbModels) {
		List<String> lines = getLinesFromLocationDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("locations.txt"));
	}
	public static void saveUsersToDatabase(List<UserDbModel> dbModels) {
		List<String> lines = getLinesFromUserDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("users.txt"));
	}
	public static void saveApartmentsToDatabase(List<ApartmentDbModel> dbModels) {
		List<String> lines = getLinesFromApartmentDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("apartments.txt"));
	}
	public static void saveApartmentAmenityPairingsToDatabase(List<ApartmentAmenityDbModel> dbModels) {
		List<String> lines = getLinesFromApartmentAmenityDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("apartmentAmenityPairings.txt"));
	}
	public static void saveAmenitiesToDatabase(List<AmenityDbModel> dbModels) {
		List<String> lines = getLinesFromAmenityDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("amenities.txt"));
	}
	
	//Get Lines From dbModels
	private static List<String> getLinesFromLocationDbModels(List<LocationDbModel> locationDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(LocationDbModel dbm : locationDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromUserDbModels(List<UserDbModel> userDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(UserDbModel dbm : userDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromApartmentDbModels(List<ApartmentDbModel> apartmentDbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ApartmentDbModel dbm : apartmentDbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromApartmentAmenityDbModels(List<ApartmentAmenityDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ApartmentAmenityDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromAmenityDbModels(List<AmenityDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(AmenityDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
}
