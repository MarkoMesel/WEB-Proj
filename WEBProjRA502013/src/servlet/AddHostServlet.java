package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.DatabaseController;
import controller.ServletController;
import model.Guest;
import model.Host;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/AddHostServlet")
public class AddHostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddHostServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletController.forwardToAddHost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateGuestRegistration(request);
		if(validationResponse.isValid()) {
			Host host = ServletController.createHostFromRequest(request);
			ContainerController.users.add(host);
			ContainerController.saveUserList();
			ServletController.forwardToHome(request, response);
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
