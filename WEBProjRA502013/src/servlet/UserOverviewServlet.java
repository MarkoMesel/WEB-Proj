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

@WebServlet("/UserOverviewServlet")
public class UserOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserOverviewServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ContainerController.populateLists();
		ArrayList<User> users = new ArrayList<User>();
		Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
		if(role == Role.ADMIN) {
			users = ContainerController.users;
		} else {
			Integer hostId = Integer.parseInt(request.getSession().getAttribute("id").toString());
			ArrayList<Reservation> reservations = ContainerController.findReservationsByHostId(hostId);
			for(Reservation reservation : reservations) {
				if(!users.contains(reservation.getGuest())) {
					users.add(reservation.getGuest());
				}
			}
		}
		ServletController.putUserListInSession(users, request.getSession());
		ServletController.forwardToUserOverview(request, response);
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
		ArrayList<User> searchResult = ContainerController.findUsersFromSearchOptions(
			users,
			userRoleSearch,
			request.getParameter("username"),
			request.getParameter("firstName"),
			request.getParameter("lastName"),
			request.getParameter("gender")
		);
		ServletController.putUserListInSession(searchResult, request.getSession());
		ServletController.forwardToUserOverview(request, response);
	}
	
}
