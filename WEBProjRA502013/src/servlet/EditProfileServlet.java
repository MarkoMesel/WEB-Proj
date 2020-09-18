package servlet;

import java.io.IOException;

import javax.annotation.processing.Messager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContainerController;
import controller.ServletController;
import message.MessageGenerator;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditProfileServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletController.forwardToEditProfile(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateEditProfile(request);
		if(validationResponse.isValid()) {
			User user = ContainerController.findUserById(Integer.parseInt((String)request.getSession().getAttribute("id")));
			ServletController.editUserFromRequest(user, request);
			ServletController.putUserInSession(user, request.getSession());
			ContainerController.saveUserList();
			ServletController.forwardToEditProfileWithSuccess(request, response, MessageGenerator.generateSuccessfulUpdateMessage());
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
