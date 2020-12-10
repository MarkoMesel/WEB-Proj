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
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/EditApartmentServlet")
public class EditApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditApartmentServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		String id = request.getParameter("currentRow").toString();
		Apartment apartment = ContainerController.findApartmentById(Integer.parseInt(id));
		ArrayList<Amenity> amenities = ContainerController.findAmenitiesByEnabled(true);
		ServletController.putApartmentAndLocationInSession(apartment, request.getSession());
		ServletController.putChosenAmenityListInSession(amenities, apartment.getAmenities(), "amenities", request.getSession());
		ServletController.forwardToEditApartment(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateApartment(request);
		if(validationResponse.isValid()) {
			Apartment apartment = ContainerController.findApartmentById(Integer.parseInt((String)request.getSession().getAttribute("apartmentId")));
			Location location = ContainerController.findLocationById(Integer.parseInt((String)request.getSession().getAttribute("locationId")));
			ServletController.editApartmentFromRequest(apartment, request);
			ServletController.editLocationFromRequest(location, request);
			ServletController.createAmenityListForApartmentFromRequest(apartment, request);
			ContainerController.saveApartmentList();
			ContainerController.saveLocationList();
			ContainerController.saveApartmentAmenitiyPairingList();
			Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
			switch(role) {
			case ADMIN:
				ArrayList<Apartment> apartments = ContainerController.findApartmentsByEnabled(true);
				ServletController.putApartmentListInSession(apartments, request.getSession());
				ServletController.forwardToApartmentOverview(request, response);
				break;
			case HOST:
				ArrayList<Apartment> activeApartments = ContainerController.findApartmentsByStatusAndHostIdAndEnabled(
					ApartmentStatus.ACTIVE, 
					hostId,
					true
				);
				ArrayList<Apartment> inactiveApartments = ContainerController.findApartmentsByStatusAndHostIdAndEnabled(
					ApartmentStatus.INACTIVE,
					hostId,
					true
				);
				ServletController.putActiveAndInactiveApartmentListsInSession(
					activeApartments,
					inactiveApartments,
					request.getSession()
				);
				ServletController.forwardToApartmentOverview(request, response);
				break;
			default:
				break;
			}
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
