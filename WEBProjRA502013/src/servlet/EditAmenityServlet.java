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

@WebServlet("/EditAmenityServlet")
public class EditAmenityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditAmenityServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		String id = request.getParameter("currentRow").toString();
		Amenity amenity = ContainerController.findAmenityById(Integer.parseInt(id));
		ServletController.putAmenityInSession(amenity, request.getSession());
		ServletController.forwardToEditAmenity(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateEditAmenity(request);
		if(validationResponse.isValid()) {
			Amenity amenity = ContainerController.findAmenityById(Integer.parseInt((String)request.getSession().getAttribute("amenityId")));
			ServletController.editAmenityFromRequest(amenity, request);
			ContainerController.saveAmenityList();
			ServletController.putAllEnabledAmenitiesInSession(request.getSession());
			ServletController.forwardToManageAmenitiesWithSuccess(request, response, MessageGenerator.generateSuccessfulCreateMessage("amenity"));
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
