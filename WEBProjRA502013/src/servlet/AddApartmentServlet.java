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
		ArrayList<Amenity> amenities = ContainerController.amenities;
		ServletController.putAmenityListInSession(amenities, request.getSession());
		ServletController.forwardToAddApartment(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Host id is: " + request.getSession().getAttribute("id"));
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateApartment(request);
		if(validationResponse.isValid()) {
			Location location = ServletController.createLocationFromRequest(request);
			ContainerController.locations.add(location);
			Apartment apartment = ServletController.createApartmentFromRequestAndLocation(request, location);
			ArrayList<Amenity> selectedAmenities = new ArrayList<Amenity>();
			for(Amenity a : ContainerController.amenities) {
				if(request.getParameter(a.name)!=null) {
					selectedAmenities.add(a);
				}
			}
			apartment.setAmenities(selectedAmenities);
			ContainerController.apartments.add(apartment);
			ContainerController.saveApartmentList();
			ContainerController.saveLocationList();
			ContainerController.saveAppartmentAmenitiyPairingsList();
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
