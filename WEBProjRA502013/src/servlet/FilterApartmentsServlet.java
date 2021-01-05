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
import model.ApartmentStatus;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/FilterApartmentsServlet")
public class FilterApartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FilterApartmentsServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ArrayList<Apartment> apartmentList;
		String role = request.getSession().getAttribute("role").toString();
		if(role.equals("HOST")) {
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
			apartmentList = ContainerController.findApartmentsByStatusAndHostIdAndEnabled(
				ApartmentStatus.ACTIVE,
				hostId,
				true
			);
		} else {
			apartmentList = ContainerController.findApartmentsByEnabled(true);
		}
		ArrayList<Amenity> amenityList = ServletController.createAmenityListFromRequest(request);
		ArrayList<Apartment> searchResult = ContainerController.filterApartmentsFromSearchOptions(
			apartmentList,
			amenityList,
			ApartmentStatus.ACTIVE,
			request.getParameter("datepickerArrive"),
			request.getParameter("datepickerLeave"),
			request.getParameter("timeArrive"),
			request.getParameter("timeLeave"),
			request.getParameter("priceMin"),
			request.getParameter("priceMax"),
			request.getParameter("roomCountMin"),
			request.getParameter("roomCountMax"),
			request.getParameter("guestCount"),
			request.getParameter("location")
		);
		ServletController.putApartmentListInSession(searchResult, request.getSession());
		ServletController.forwardToApartmentOverview(request, response);
	}
	
}
