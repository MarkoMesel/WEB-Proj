package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbm.AmenityDbModel;
import dbm.ApartmentDbModel;
import dbm.UserDbModel;
import message.MessageGenerator;
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

public class ServletController {
	
	public static Guest createGuestFromRequest(HttpServletRequest request) {
		return new Guest(
			ContainerController.users.size()+1,
			request.getParameter("username"),
			request.getParameter("password"),
			request.getParameter("firstName"),
			request.getParameter("lastName"),
			Gender.valueOf(request.getParameter("gender")),
			true
		);
	}
	public static void putUserInSession(User user, HttpSession session) {
		UserDbModel dbm = DatabaseController.createModelFromUser(user);
		session.setAttribute("id", dbm.id);
		session.setAttribute("username", dbm.username);
		session.setAttribute("password", dbm.password);
		session.setAttribute("firstName", dbm.firstName);
		session.setAttribute("lastName", dbm.lastName);
		session.setAttribute("gender", dbm.gender);
		session.setAttribute("role", dbm.role);
		session.setAttribute("enabled", dbm.enabled);
	}
	public static void putUserListInSession(ArrayList<User> users, HttpSession session) {
		ArrayList<UserDbModel> dbmList = new ArrayList<UserDbModel>();
		for(User u : users)
			dbmList.add(DatabaseController.createModelFromUser(u));
		session.setAttribute("users", dbmList);
	}
	public static void forwardToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	public static void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	public static void forwardToLoginWithError(HttpServletRequest request, HttpServletResponse response,
			String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/login.jsp").forward(request, response);	
	}
	public static void forwardToEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
	}
	public static void forwardToUserOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/userOverview.jsp").forward(request, response);
	}
	public static void invalidateSession(HttpSession session) {
		session.invalidate();
	}
	public static void sendBadRequest(HttpServletResponse response, String errorMessage) throws IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
	}
	public static void editUserFromRequest(User user, HttpServletRequest request) {
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setGender(Gender.valueOf(request.getParameter("gender")));
	}
	public static void forwardToEditProfileWithSuccess(HttpServletRequest request, HttpServletResponse response,
			String successMessage) throws ServletException, IOException {
		request.setAttribute("successMessage", successMessage);
		request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
	}
	public static void putApartmentListInSession(ArrayList<Apartment> apartments, HttpSession session) {
		ArrayList<ApartmentRequestModel> dbmList = new ArrayList<ApartmentRequestModel>();
		for(Apartment a : apartments)
			dbmList.add(DatabaseController.createRequestModelFromApartment(a));
		session.setAttribute("apartments", dbmList);
	}
	public static void forwardToApartmentOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/apartmentOverview.jsp").forward(request, response);
	}
	public static void forwardToAddApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addApartment.jsp").forward(request, response);
	}
	public static void putAmenityListInSession(ArrayList<Amenity> amenities, HttpSession session) {
		ArrayList<AmenityDbModel> dbmList = new ArrayList<AmenityDbModel>();
		for(Amenity a : amenities) {
			dbmList.add(DatabaseController.createModelFromAmenity(a));
		}
		session.setAttribute("amenities", dbmList);
	}
	public static Location createLocationFromRequest(HttpServletRequest request) {
		return new Location(
			ContainerController.locations.size()+1,
			Float.parseFloat(request.getParameter("latitude")),
			Float.parseFloat(request.getParameter("longitude")),
			request.getParameter("streetName"),
			request.getParameter("streetNumber"),
			request.getParameter("city"),
			request.getParameter("postNumber")
		);
	}
	public static Apartment createApartmentFromRequestAndLocation(HttpServletRequest request, Location location) {		
		return new Apartment(
			ContainerController.apartments.size()+1,
			Type.valueOf(request.getParameter("aType")),
			Integer.parseInt(request.getParameter("roomCount")),
			Integer.parseInt(request.getParameter("guestCount")),
			location,
			(Host) ContainerController.findUserById(Integer.parseInt(request.getSession().getAttribute("id").toString())),
			request.getParameter("price"),
			request.getParameter("checkInTime"),
			request.getParameter("checkOutTime"),
			Status.INACTIVE
		);
	}
}
