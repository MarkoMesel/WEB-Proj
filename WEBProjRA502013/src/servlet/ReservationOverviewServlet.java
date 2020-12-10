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
import model.ReservationStatus;
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/ReservationOverviewServlet")
public class ReservationOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ReservationOverviewServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
		Integer id = Integer.parseInt(request.getSession().getAttribute("id").toString());
		ArrayList<Reservation> reservations = new ArrayList<>();
		switch(role) {
		case ADMIN:
			reservations = ContainerController.reservations;
			break;
		case HOST:
			reservations = ContainerController.findReservationsByHostId(id);
			break;
		case GUEST:
			reservations = ContainerController.findReservationsByGuestId(id);
			break;
		default:
			break;
		}
		ServletController.putReservationListInSession(reservations, request.getSession());
		ServletController.forwardToReservationOverview(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
		Integer id = Integer.parseInt(request.getSession().getAttribute("id").toString());
		ArrayList<Reservation> reservations = new ArrayList<>();
		String reservationId = request.getParameter("currentRow").toString();
		Reservation reservation = ContainerController.findReservationById(Integer.parseInt(reservationId));
		String btnLabel = null;
		switch(role) {
		case ADMIN:
			reservations = ContainerController.reservations;
			break;
		case HOST:
			if(request.getParameter("acceptBtn") != null) {
				reservation.setStatus(ReservationStatus.ACCEPTED);
			} else if(request.getParameter("rejectBtn") != null) {
				reservation.setStatus(ReservationStatus.REJECTED);
			} else if(request.getParameter("finishBtn") != null) {
				reservation.setStatus(ReservationStatus.FINISHED);
			}
			reservations = ContainerController.findReservationsByHostId(id);
			break;
		case GUEST:
			if(request.getParameter("cancelBtn") != null) {
				reservation.setStatus(ReservationStatus.CANCELED);
			}
			reservations = ContainerController.findReservationsByGuestId(id);
			break;
		default:
			break;
		}
		ContainerController.saveReservationsList();
		ServletController.putReservationListInSession(reservations, request.getSession());
		ServletController.forwardToReservationOverview(request, response);
	}
	
}
