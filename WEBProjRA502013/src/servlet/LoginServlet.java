package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.ServletController;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateUserList();
		ValidationResponse validationResponse = Validator.validateLogin(request);
		if(validationResponse.isValid()) {
			User user = ContainerController.findUserByUsernameAndPassword(
					request.getParameter("username"),
					request.getParameter("password")
			);
			ServletController.putUserInSession(user, request.getSession());
			ServletController.redirectToHome(request, response);
		} else {
			ServletController.redirectToLoginWithError(
				request, 
				response, 
				validationResponse.getErrorMessage());
		}
	}
}
