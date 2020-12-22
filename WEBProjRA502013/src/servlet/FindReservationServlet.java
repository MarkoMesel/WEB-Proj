package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.ServletController;
import model.Apartment;
import model.ApartmentStatus;
import model.Reservation;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/FindReservationServlet")
public class FindReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FindReservationServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ArrayList<Reservation> reservationList;
		String role = request.getSession().getAttribute("role").toString();
		if(role.equals("HOST")) {
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
			reservationList = ContainerController.findReservationsByHostId(hostId);
		} else {
			reservationList = ContainerController.reservations;
		}
		ArrayList<Reservation> searchResult = ContainerController.findReservationsFromGuestUsername(
			reservationList,
			request.getParameter("username")
		);
		ServletController.putReservationListInSession(searchResult, request.getSession());
		ServletController.forwardToReservationOverview(request, response);
	}
	
}
