package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbm.AmenityDbModel;
import dbm.ApartmentDbModel;
import dbm.LocationDbModel;
import dbm.UserDbModel;
import helper.PriceManager;
import model.Amenity;
import model.Apartment;
import model.Gender;
import model.Guest;
import model.Host;
import model.Location;
import model.Reservation;
import model.ReservationStatus;
import model.ApartmentStatus;
import model.Comment;
import model.Type;
import model.User;
import tablemodel.AmenityTableModel;
import tablemodel.ApartmentTableModel;
import tablemodel.CommentTableModel;
import tablemodel.ReservationTableModel;

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
	public static void forwardToEditApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/editApartment.jsp").forward(request, response);
	}
	
	//Forward to Reservation pages
	public static void forwardToReserveApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/reserveApartment.jsp").forward(request, response);
	}
	public static void forwardToReservationOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/reservationOverview.jsp").forward(request, response);
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
	
	//Forward to Comment management pages
	public static void forwardToViewComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/viewComments.jsp").forward(request, response);	
	}
	public static void forwardToAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addComment.jsp").forward(request, response);	
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
			request.getParameter("postNumber"),
			true
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
			ApartmentStatus.INACTIVE,
			true
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
	public static void createAmenityListForApartmentFromRequest(Apartment apartment, HttpServletRequest request) {
		ArrayList<Amenity> selectedAmenities = new ArrayList<Amenity>();
		for(Amenity a : ContainerController.amenities) {
			if(request.getParameter(a.name)!=null) {
				selectedAmenities.add(a);
			}
		}
		apartment.setAmenities(selectedAmenities);
	}
	public static Reservation createReservationFromRequest(HttpServletRequest request) {
		Apartment apartment = ContainerController.findApartmentById(Integer.parseInt((String)request.getSession().getAttribute("apartmentId")));
		Guest guest = (Guest) ContainerController.findUserById(Integer.parseInt((String)request.getSession().getAttribute("id")));
		Integer numberOfNights = Integer.parseInt(request.getParameter("numberOfNights"));
		String totalPrice = PriceManager.calculateTotalPrice(apartment, numberOfNights);
		return new Reservation(
			ContainerController.reservations.size()+1,
			apartment,
			request.getParameter("datepicker"),
			numberOfNights,
			totalPrice,
			request.getParameter("reservationMessage"),
			guest,
			ReservationStatus.CREATED
		);
	}
	public static Comment createCommentFromRequest(HttpServletRequest request) {
		Apartment apartment = ContainerController.findApartmentById(Integer.parseInt((String)request.getSession().getAttribute("apartmentId")));
		Guest guest = (Guest) ContainerController.findUserById(Integer.parseInt((String)request.getSession().getAttribute("id")));
		return new Comment(
			ContainerController.comments.size()+1,
			guest,
			apartment,
			request.getParameter("commentMessage"),
			Double.parseDouble(request.getParameter("commentRating")),
			false
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
	public static void editApartmentFromRequest(Apartment apartment, HttpServletRequest request) {
		apartment.setType(Type.valueOf(request.getParameter("aType")));
		if(Type.valueOf(request.getParameter("aType")) != Type.ROOM) {
			apartment.setRoomCount(Integer.parseInt(request.getParameter("roomCount")));
		} else {
			apartment.setRoomCount(1);
		}
		apartment.setGuestCount(Integer.parseInt(request.getParameter("guestCount")));
		apartment.setPrice(request.getParameter("guestCount"));
		apartment.setCheckInTime(request.getParameter("checkInTime"));
		apartment.setCheckOutTime(request.getParameter("checkOutTime"));
	}
	public static void editLocationFromRequest(Location location, HttpServletRequest request) {
		location.setLatitude(Float.parseFloat(request.getParameter("latitude")));
		location.setLongitude(Float.parseFloat(request.getParameter("longitude")));
		location.setStreetName(request.getParameter("streetName"));
		location.setStreetNumber(request.getParameter("streetNumber"));
		location.setCity(request.getParameter("city"));
		location.setPostNumber(request.getParameter("postNumber"));
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
	public static void putChosenAmenityListInSession(ArrayList<Amenity> amenities, ArrayList<Amenity> chosenAmenities, String amenityListattrName, HttpSession session) {
		ArrayList<AmenityTableModel> dbmList = new ArrayList<AmenityTableModel>();
		String checked;
		for(Amenity a : amenities) {
			Amenity a0 = ContainerController.findChosenAmenityById(chosenAmenities, a.getId());
			if(a0 == null) {
				checked = "false";
			} else {
				checked = "true";
			}
			dbmList.add(ContainerController.createTableModelFromApartmentAndAmenity(a, checked));
		}
		session.setAttribute(amenityListattrName, dbmList);
	}
	public static void putApartmentAndLocationInSession(Apartment apartment, HttpSession session) {
		putApartmentInSession(apartment, session);
		putLocationInSession(apartment.getLocation(), session);
	}
	public static void putApartmentInSession(Apartment apartment, HttpSession session) {
		ApartmentDbModel adbm = ContainerController.createModelFromApartment(apartment);
		session.setAttribute("apartmentId", adbm.id);
		session.setAttribute("apartmentType", adbm.type);
		session.setAttribute("apartmentRoomCount", adbm.roomCount);
		session.setAttribute("apartmentGuestCount", adbm.guestCount);
		session.setAttribute("apartmentPrice", adbm.price);
		session.setAttribute("apartmentCheckInTime", adbm.checkInTime);
		session.setAttribute("apartmentCheckOutTime", adbm.checkOutTime);
		session.setAttribute("apartmentStatus", adbm.status);
	}
	public static void putApartmentAndDateListInSession(Apartment apartment, ArrayList<String> apartmentDates, HttpSession session) {
		putApartmentInSession(apartment, session);
		putDateListInSession(apartmentDates, session);
	}
	public static void putDateListInSession(ArrayList<String> apartmentDates, HttpSession session) {
		session.setAttribute("apartmentDates", apartmentDates);
	}
	public static void putLocationInSession(Location location, HttpSession session) {
		LocationDbModel ldbm = ContainerController.createModelFromLocation(location);
		session.setAttribute("locationId", ldbm.id);
		session.setAttribute("locationLatitude", ldbm.latitude);
		session.setAttribute("locationLongitude", ldbm.longitude);
		session.setAttribute("locationStreetName", ldbm.streetName);
		session.setAttribute("locationStreetNumber", ldbm.streetNumber);
		session.setAttribute("locationCity", ldbm.city);
		session.setAttribute("locationPostNumber", ldbm.postNumber);
	}
	public static void putApartmentListInSession(ArrayList<Apartment> apartments, HttpSession session) {
		ArrayList<ApartmentTableModel> dbmList = new ArrayList<ApartmentTableModel>();
		for(Apartment a : apartments)
			dbmList.add(ContainerController.createTableModelFromApartment(a));
		session.setAttribute("apartments", dbmList);
	}
	public static void putActiveAndInactiveApartmentListsInSession(ArrayList<Apartment> activeApartments,
			ArrayList<Apartment> inactiveApartments, HttpSession session) {
		ArrayList<ApartmentTableModel> dbmListActive = new ArrayList<ApartmentTableModel>();
		for(Apartment a : activeApartments)
			dbmListActive.add(ContainerController.createTableModelFromApartment(a));
		session.setAttribute("apartments", dbmListActive);
		ArrayList<ApartmentTableModel> dbmListInactive = new ArrayList<ApartmentTableModel>();
		for(Apartment a : inactiveApartments)
			dbmListInactive.add(ContainerController.createTableModelFromApartment(a));
		session.setAttribute("inactiveApartments", dbmListInactive);
	}
	public static void putApartmentTableModelListInSession(ArrayList<ApartmentTableModel> apartmentTableModels,
			String apartmentAttrName, HttpSession session) {
		ArrayList<ApartmentTableModel> dbmList = new ArrayList<ApartmentTableModel>();
		for(ApartmentTableModel a : apartmentTableModels)
			dbmList.add(a);
		session.setAttribute(apartmentAttrName, dbmList);
	}
	public static void putAmenityInSession(Amenity amenity, HttpSession session) {
		AmenityDbModel dbm = ContainerController.createModelFromAmenity(amenity);
		session.setAttribute("amenityId", dbm.id);
		session.setAttribute("amenityName", dbm.name);
		session.setAttribute("amenityDetails", dbm.details);
		session.setAttribute("amenityEnabled", dbm.enabled);
	}
	public static void putAmenityListInSession(ArrayList<Amenity> amenities, String amenityListattrName, HttpSession session) {
		ArrayList<AmenityDbModel> dbmList = new ArrayList<AmenityDbModel>();
		for(Amenity a : amenities) {
			dbmList.add(ContainerController.createModelFromAmenity(a));
		}
		session.setAttribute(amenityListattrName, dbmList);
	}
	public static void putAllEnabledAmenitiesInSession(HttpSession session) {
		ArrayList<AmenityDbModel> dbmList = new ArrayList<AmenityDbModel>();
		ArrayList<Amenity> amenities = ContainerController.findAmenitiesByEnabled(true);
		for(Amenity a : amenities) {
			dbmList.add(ContainerController.createModelFromAmenity(a));
		}
		session.setAttribute("amenities", dbmList);
	}
	public static void putReservationListInSession(ArrayList<Reservation> reservations, HttpSession session) {
		ArrayList<ReservationTableModel> dbmList = new ArrayList<ReservationTableModel>();
		for(Reservation r : reservations)
			dbmList.add(ContainerController.createTableModelFromReservation(r));
		session.setAttribute("reservations", dbmList);
	}
	public static void putCommentListInSession(ArrayList<Comment> comments, HttpSession session) {
		ArrayList<CommentTableModel> dbmList = new ArrayList<CommentTableModel>();
		for(Comment c : comments)
			dbmList.add(ContainerController.createTableModelFromComment(c));
		session.setAttribute("comments", dbmList);
	}
	public static void putCommentPermissionInSession(String permission, HttpSession session) {
		session.setAttribute("commentPermission", permission);
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
