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
import model.User;

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
		ArrayList<Apartment> apartments = new ArrayList<Apartment>();
		if(ContainerController.isAdmin(request.getSession().getAttribute("role").toString())) {
			apartments = ContainerController.apartments;
		} else if(ContainerController.isHost(request.getSession().getAttribute("role").toString())) {
			//TO DO
		} else {
			apartments = ContainerController.findApartmentsByStatus("ACTIVE");
		}
		ServletController.putApartmentListInSession(apartments, request.getSession());
		ServletController.forwardToApartmentOverview(request, response);
//		ArrayList<User> users = new ArrayList<User>();
//		if(ContainerController.isUserAdmin(request.getSession().getAttribute("role").toString())) {
//			users = ContainerController.users;
//		}
//		ServletController.putUserListInSession(users, request.getSession());
//		ServletController.forwardToUserOverview(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ArrayList<Apartment> apartmentList = ContainerController.apartments;
		ArrayList<Apartment> searchResult = ContainerController.findApartmentsByOptionalTypeAndAndOptionalGuestCountAndOptionalPriceLessThan(
			apartmentList,
			request.getParameter("type"),
			request.getParameter("guestCount"),
			request.getParameter("price")
		);
		ServletController.putApartmentListInSession(searchResult, request.getSession());
		ServletController.forwardToApartmentOverview(request, response);
	}
	
}
