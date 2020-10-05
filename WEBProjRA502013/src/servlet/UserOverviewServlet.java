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
		if(ContainerController.isAdmin(request.getSession().getAttribute("role").toString())) {
			users = ContainerController.users;
		}
		ServletController.putUserListInSession(users, request.getSession());
		ServletController.forwardToUserOverview(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ArrayList<User> users = new ArrayList<User>();
		if(ContainerController.isAdmin(request.getSession().getAttribute("role").toString())) {
			users = ContainerController.users;
		}
		ArrayList<User> searchResult = ContainerController.findUsersByOptionalUsernameAndOptionalFirstNameAndOptionalLastNameAndOptionalGender(
			users,
			request.getParameter("username"),
			request.getParameter("firstName"),
			request.getParameter("lastName"),
			request.getParameter("gender")
		);
		ServletController.putUserListInSession(searchResult, request.getSession());
		ServletController.forwardToUserOverview(request, response);
	}
	
}
