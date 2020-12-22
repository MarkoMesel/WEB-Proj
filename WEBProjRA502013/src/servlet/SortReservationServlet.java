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
import tablemodel.ReservationTableModel;
import tablemodel.ReservationTableParameter;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/SortReservationServlet")
public class SortReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SortReservationServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		
		String atpLabel = request.getParameter("sortBtn"); 
		Boolean reversed;
		if(request.getSession().getAttribute("sortTypeRes") == null) 
			reversed = true;
		else
			reversed = !Boolean.parseBoolean(request.getSession().getAttribute("sortTypeRes").toString());
		@SuppressWarnings("unchecked")
		ArrayList<ReservationTableModel> reservationList = (ArrayList<ReservationTableModel>) request.getSession().getAttribute("reservations");
		ArrayList<ReservationTableModel> sortedReservationList = ContainerController.sortTableReservationList(
			reservationList,
			ReservationTableParameter.valueOfLabel(atpLabel),
			reversed
		);
		ServletController.putReversedBooleanInSession(reversed, "sortTypeRes", request.getSession());
		ServletController.putReservationTableModelListInSession(sortedReservationList, request.getSession());
		ServletController.forwardToReservationOverview(request, response);
	}
	
}
