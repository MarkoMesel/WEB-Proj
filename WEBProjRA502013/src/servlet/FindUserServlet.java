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
import model.Reservation;
import model.Role;
import model.User;

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FindUserServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ArrayList<User> users = new ArrayList<User>();
		Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
		String userRoleSearch;
		if(role == Role.ADMIN) {
			users = ContainerController.users;
			userRoleSearch = request.getParameter("userRoleSearch");
		}else {
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
			ArrayList<Reservation> reservations = ContainerController.findReservationsByHostId(hostId);
			for(Reservation reservation : reservations) {
				if(!users.contains(reservation.getGuest())) {
					users.add(reservation.getGuest());
				}
			}
			userRoleSearch = "GUEST";
		}
		User searchResultSingle = ContainerController.findUserFromSearchOptions(
			users,
			userRoleSearch,
			request.getParameter("username"),
			request.getParameter("firstName"),
			request.getParameter("lastName"),
			request.getParameter("gender")
		);
		ArrayList<User> searchResult = new ArrayList<User>();
		if(searchResultSingle != null)
			searchResult.add(searchResultSingle);
		ServletController.putUserListInSession(searchResult, request.getSession());
		ServletController.forwardToUserOverview(request, response);
	}
	
}
