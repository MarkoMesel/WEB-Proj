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
import model.User;
import tablemodel.ApartmentTableModel;
import tablemodel.ApartmentTableParameter;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/SortApartmentServlet")
public class SortApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SortApartmentServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		
		String atpLabel = request.getParameter("sortBtn"); 
		
		if(atpLabel != null) {
			@SuppressWarnings("unchecked")
			ArrayList<ApartmentTableModel> apartmentList = (ArrayList<ApartmentTableModel>) request.getSession().getAttribute("apartments");
			ArrayList<ApartmentTableModel> sortedApartmentList = ContainerController.sortTableApartmentList(
				apartmentList,
				ApartmentTableParameter.valueOfLabel(atpLabel)
			);
			ServletController.putApartmentTableModelListInSession(sortedApartmentList, "apartments", request.getSession());
			ServletController.forwardToApartmentOverview(request, response);
		} else {
			atpLabel = request.getParameter("sortInactiveBtn");
			@SuppressWarnings("unchecked")
			ArrayList<ApartmentTableModel> apartmentList = (ArrayList<ApartmentTableModel>) request.getSession().getAttribute("inactiveApartments");
			ArrayList<ApartmentTableModel> sortedApartmentList = ContainerController.sortTableApartmentList(
				apartmentList,
				ApartmentTableParameter.valueOfLabel(atpLabel)
			);
			ServletController.putApartmentTableModelListInSession(sortedApartmentList, "inactiveApartments", request.getSession());
			ServletController.forwardToApartmentOverview(request, response);
		}
	}
	
}
