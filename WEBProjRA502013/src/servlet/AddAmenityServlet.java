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
import model.Location;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/AddAmenityServlet")
public class AddAmenityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddAmenityServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ServletController.forwardToAddAmenity(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateAmenity(request);
		if(validationResponse.isValid()) {
			Amenity amenity = ServletController.createAmenityFromRequest(request);
			ContainerController.amenities.add(amenity);
			ContainerController.saveAmenityList();
			ServletController.putAllEnabledAmenitiesInSession(request.getSession());
			ServletController.forwardToManageAmenitiesWithSuccess(request, response, MessageGenerator.generateSuccessfulUpdateMessage("the selected amenity"));
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
