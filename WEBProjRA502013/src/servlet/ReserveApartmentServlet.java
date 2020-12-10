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
import message.MessageGenerator;
import model.Amenity;
import model.Apartment;
import model.ApartmentStatus;
import model.Location;
import model.Reservation;
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/ReserveApartmentServlet")
public class ReserveApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ReserveApartmentServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		String id = request.getParameter("currentRow").toString();
		Apartment apartment = ContainerController.findApartmentById(Integer.parseInt(id));
		//ArrayList<String> apartmentDates = apartment.getDatesForReservation();
		ArrayList<String> apartmentDates = apartment.getBusyDates();
/*
		apartmentDates.add("02-12-2020");
		apartmentDates.add("03-12-2020");
		apartmentDates.add("04-12-2020");
		apartmentDates.add("05-12-2020");
*/	
		ServletController.putApartmentAndDateListInSession(apartment, apartmentDates, request.getSession());
		ServletController.forwardToReserveApartment(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateApartmentReservation(request);
		//TODO - finish validation
		if(validationResponse.isValid()) {
			Reservation reservation = ServletController.createReservationFromRequest(request);
			ContainerController.reservations.add(reservation);
			ContainerController.saveReservationsList();
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
