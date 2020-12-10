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
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/ApartmentOverviewServlet")
public class ApartmentOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ApartmentOverviewServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
		ArrayList<Apartment> apartments;
		switch(role) {
		case ADMIN:
			apartments = ContainerController.findApartmentsByEnabled(true);
			ServletController.putApartmentListInSession(apartments, request.getSession());
			ServletController.forwardToApartmentOverview(request, response);
			break;
		case HOST:
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
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
		case GUEST:
			apartments = ContainerController.findApartmentsByStatusAndEnabled("ACTIVE", true);
			ServletController.putApartmentListInSession(apartments, request.getSession());
			ServletController.forwardToApartmentOverview(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
