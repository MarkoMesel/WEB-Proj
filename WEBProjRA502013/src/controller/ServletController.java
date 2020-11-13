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
	//Forward to Home
	public static void forwardToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	//Forward to Login 
	public static void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	public static void forwardToLoginWithError(HttpServletRequest request, HttpServletResponse response,
			String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/login.jsp").forward(request, response);	
	}
	
	//Forward to profile and user management pages
	public static void forwardToEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
	}
	public static void forwardToEditProfileWithSuccess(HttpServletRequest request, HttpServletResponse response,
			String successMessage) throws ServletException, IOException {
		request.setAttribute("successMessage", successMessage);
		request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
	}
	public static void forwardToUserOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/userOverview.jsp").forward(request, response);
	}
	
	//Forward to Apartment management pages
	public static void forwardToApartmentOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/apartmentOverview.jsp").forward(request, response);
	}
	public static void forwardToAddApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addApartment.jsp").forward(request, response);
	}

	//Forward to Amenity management pages
	public static void forwardToManageAmenities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/manageAmenities.jsp").forward(request, response);
	}
	public static void forwardToManageAmenitiesWithSuccess(HttpServletRequest request, HttpServletResponse response,
			String successMessage) throws ServletException, IOException {
		request.setAttribute("successMessage", successMessage);
		request.getRequestDispatcher("/manageAmenities.jsp").forward(request, response);
	}
	public static void forwardToAddAmenity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addAmenity.jsp").forward(request, response);
	}
	public static void forwardToEditAmenity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/editAmenity.jsp").forward(request, response);
	}
	
	//Create from request (+ optional)
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
	public static Amenity createAmenityFromRequest(HttpServletRequest request) {
		return new Amenity(
			ContainerController.amenities.size()+1,
			request.getParameter("amenityName"),
			request.getParameter("amenityDetails"),
			true
		);
	}
	
	//Edit from request
	public static void editUserFromRequest(User user, HttpServletRequest request) {
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setGender(Gender.valueOf(request.getParameter("gender")));
	}
	public static void editAmenityFromRequest(Amenity amenity, HttpServletRequest request) {
		amenity.setName(request.getParameter("amenityName"));
		amenity.setDetails(request.getParameter("amenityDetails"));
	}
	
	//Put in session
	public static void putUserInSession(User user, HttpSession session) {
		UserDbModel dbm = ContainerController.createModelFromUser(user);
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
			dbmList.add(ContainerController.createModelFromUser(u));
		session.setAttribute("users", dbmList);
	}
	public static void putApartmentListInSession(ArrayList<Apartment> apartments, HttpSession session) {
		ArrayList<ApartmentRequestModel> dbmList = new ArrayList<ApartmentRequestModel>();
		for(Apartment a : apartments)
			dbmList.add(DatabaseController.createRequestModelFromApartment(a));
		session.setAttribute("apartments", dbmList);
	}
	public static void putAmenityInSession(Amenity amenity, HttpSession session) {
		AmenityDbModel dbm = DatabaseController.createModelFromAmenity(amenity);
		session.setAttribute("amenityId", dbm.id);
		session.setAttribute("amenityName", dbm.name);
		session.setAttribute("amenityDetails", dbm.details);
		session.setAttribute("amenityEnabled", dbm.enabled);
	}
	public static void putAmenityListInSession(ArrayList<Amenity> amenities, HttpSession session) {
		ArrayList<AmenityDbModel> dbmList = new ArrayList<AmenityDbModel>();
		for(Amenity a : amenities) {
			dbmList.add(DatabaseController.createModelFromAmenity(a));
		}
		session.setAttribute("amenities", dbmList);
	}
	public static void putAllEnabledAmenitiesInSession(HttpSession session) {
		ArrayList<AmenityDbModel> dbmList = new ArrayList<AmenityDbModel>();
		ArrayList<Amenity> amenities = ContainerController.findAmenitiesByEnabled(true);
		for(Amenity a : amenities) {
			dbmList.add(DatabaseController.createModelFromAmenity(a));
		}
		session.setAttribute("amenities", dbmList);
	}

	//Invalidate session
	public static void invalidateSession(HttpSession session) {
		session.invalidate();
	}
	
	//Send error
	public static void sendBadRequest(HttpServletResponse response, String errorMessage) throws IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
	}


}
