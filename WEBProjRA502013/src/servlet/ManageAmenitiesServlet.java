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

@WebServlet("/ManageAmenitiesServlet")
public class ManageAmenitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ManageAmenitiesServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		ServletController.putAllEnabledAmenitiesInSession(request.getSession());
		ServletController.forwardToManageAmenities(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
