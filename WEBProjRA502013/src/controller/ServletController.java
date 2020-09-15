package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbm.UserDbModel;
import message.MessageGenerator;
import model.Gender;
import model.Guest;
import model.User;

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
	public static void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	public static void sendBadRequest(HttpServletResponse response, String errorMessage) throws IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
	}
	public static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	public static void redirectToLoginWithError(HttpServletRequest request, HttpServletResponse response,
			String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/login.jsp").forward(request, response);	
	}
	public static void invalidateSession(HttpSession session) {
		session.invalidate();
	}
	
}
