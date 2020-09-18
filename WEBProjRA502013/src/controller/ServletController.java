package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbm.ApartmentDbModel;
import dbm.UserDbModel;
import message.MessageGenerator;
import model.Apartment;
import model.Gender;
import model.Guest;
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
}
