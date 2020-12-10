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
import dbm.CommentDbModel;
import dbm.LocationDbModel;
import dbm.ReservationDbModel;
import dbm.UserDbModel;
import listener.ContextListener;
import model.Location;

public class DatabaseController {
	//FIELDS
	
	//Misc
	public static final String PATH_TO_RESOURCES = "WEBProjRA502013/WebContent/WEB-INF/resources/";
	public static ServletContext servletContext = ContextListener.context;
	
	//METHODS
	
	//Get from database
	public static List<LocationDbModel> getLocationsFromDatabase() {
		List<String> locationLines = getLinesFromFile(getPathToFile("locations.txt"));
		return getLocationDbModelsFromLines(locationLines);
	}
	public static List<UserDbModel> getUsersFromDatabase() {
		List<String> lines = getLinesFromFile(getPathToFile("users.txt"));
		return getUserDbModelsFromLines(lines);
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
	public static List<ReservationDbModel> getReservationsFromDatabase() {
		List<String> lines = getLinesFromFile(getPathToFile("reservations.txt"));
		return getReservationDbModelsFromLines(lines);
	}
	public static List<CommentDbModel> getCommentsFromDatabase() {
		List<String> lines = getLinesFromFile(getPathToFile("comments.txt"));
		return getCommentDbModelsFromLines(lines);
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
	public static void saveReservationsToDatabase(List<ReservationDbModel> dbModels) {
		List<String> lines = getLinesFromReservationDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("reservations.txt"));
	}
	public static void saveCommentsToDatabase(List<CommentDbModel> dbModels) {
		List<String> lines = getLinesFromCommentDbModels(dbModels);
		saveLinesToFile(lines, getPathToFile("comments.txt"));
	}
	
	//Get dbModels from lines
	private static List<LocationDbModel> getLocationDbModelsFromLines(List<String> locationLines) {
		ArrayList<LocationDbModel> dbModels = new ArrayList<LocationDbModel>();
		for(String line : locationLines)
			dbModels.add(new LocationDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<UserDbModel> getUserDbModelsFromLines(List<String> lines) {
		ArrayList<UserDbModel> dbModels = new ArrayList<UserDbModel>();
		for(String line : lines)
			dbModels.add(new UserDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<ApartmentDbModel> getApartmentDbModelsFromLines(List<String> lines) {
		ArrayList<ApartmentDbModel> dbModels = new ArrayList<ApartmentDbModel>();
		for(String line : lines)
			dbModels.add(new ApartmentDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<AmenityDbModel> getAmenityDbModelsFromLines(List<String> lines) {
		ArrayList<AmenityDbModel> dbModels = new ArrayList<AmenityDbModel>();
		for(String line : lines)
			dbModels.add(new AmenityDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<ApartmentAmenityDbModel> getApartmentAmenityDbModelsFromLines(List<String> lines) {
		ArrayList<ApartmentAmenityDbModel> dbModels = new ArrayList<ApartmentAmenityDbModel>();
		for(String line : lines)
			dbModels.add(new ApartmentAmenityDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<ReservationDbModel> getReservationDbModelsFromLines(List<String> lines) {
		ArrayList<ReservationDbModel> dbModels = new ArrayList<ReservationDbModel>();
		for(String line : lines)
			dbModels.add(new ReservationDbModel(splitLine(line)));
		return dbModels;
	}
	private static List<CommentDbModel> getCommentDbModelsFromLines(List<String> lines) {
		ArrayList<CommentDbModel> dbModels = new ArrayList<CommentDbModel>();
		for(String line : lines)
			dbModels.add(new CommentDbModel(splitLine(line)));
		return dbModels;
	}
	
	//Get Lines From dbModels
	private static List<String> getLinesFromLocationDbModels(List<LocationDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(LocationDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromUserDbModels(List<UserDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(UserDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromApartmentDbModels(List<ApartmentDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ApartmentDbModel dbm : dbModels)
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
	private static List<String> getLinesFromReservationDbModels(List<ReservationDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(ReservationDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	private static List<String> getLinesFromCommentDbModels(List<CommentDbModel> dbModels) {
		ArrayList<String> lines = new ArrayList<String>();
		for(CommentDbModel dbm : dbModels)
			lines.add(mergeIntoLine(dbm));
		return lines;
	}
	
	//Save to file
	public static void saveLinesToFile(List<String> lines, String file) {
		try {
			Files.write(Paths.get(file), lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}
	
	//String generators
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
	public static String getLocationAsString(Location l) {
		return l.getStreetName() + " " 
			+ l.getStreetNumber() + ", " 
			+ l.getPostNumber() + " " 
			+ l.getCity() + "(" 
			+ l.getLatitude() + ", " 
			+ l.getLongitude() + ")";
	}
	
}
