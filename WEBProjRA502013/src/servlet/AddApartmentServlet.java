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
import model.Amenity;
import model.Apartment;
import model.Location;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/AddApartmentServlet")
public class AddApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddApartmentServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		ArrayList<Amenity> amenities = ContainerController.findAmenitiesByEnabled(true);
		ServletController.putAmenityListInSession(amenities, "amenities", request.getSession());
		ServletController.forwardToAddApartment(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateApartment(request);
		if(validationResponse.isValid()) {
			Location location = ServletController.createLocationFromRequest(request);
			ContainerController.locations.add(location);
			Apartment apartment = ServletController.createApartmentFromRequestAndLocation(request, location);
			ServletController.createAmenityListForApartmentFromRequest(apartment, request);
			ContainerController.apartments.add(apartment);
			ContainerController.saveApartmentList();
			ContainerController.saveLocationList();
			ContainerController.saveApartmentAmenitiyPairingList();
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
