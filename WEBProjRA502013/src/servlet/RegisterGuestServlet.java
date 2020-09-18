package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.ServletController;
import model.Guest;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/RegisterGuestServlet")
public class RegisterGuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegisterGuestServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateGuestRegistration(request);
		if(validationResponse.isValid()) {
			Guest guest = ServletController.createGuestFromRequest(request);
			ContainerController.addUser(guest);
			ServletController.putUserInSession(guest, request.getSession());
			ContainerController.saveUserList();
			ServletController.forwardToHome(request, response);
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
