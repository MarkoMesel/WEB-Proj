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

@WebServlet("/DeleteAmenityServlet")
public class DeleteAmenityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteAmenityServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		String id = request.getParameter("currentSelectedAmenity").toString();
		Amenity amenity = ContainerController.findAmenityById(Integer.parseInt(id));
		ContainerController.logicalDeleteAmenity(amenity);
		ContainerController.saveAmenityList();
		ContainerController.saveAppartmentAmenitiyPairingList();
		ServletController.putAllEnabledAmenitiesInSession(request.getSession());
		ServletController.forwardToManageAmenitiesWithSuccess(request, response, MessageGenerator.generateSuccessfulDeleteMessage("amenity"));
	}
}
