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
import message.MessageGenerator;
import model.Amenity;
import model.Apartment;
import model.Comment;
import model.Location;
import model.Reservation;
import model.ReservationStatus;
import model.Role;
import model.User;
import validator.ValidationResponse;
import validator.Validator;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddCommentServlet() {
        super();
    }
	
	public void init() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletController.forwardToAddComment(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContainerController.populateLists();
		ValidationResponse validationResponse = Validator.validateComment(request);
		if(validationResponse.isValid()) {
			Comment comment = ServletController.createCommentFromRequest(request);
			if(ContainerController.addComment(comment)) {
				//ContainerController.comments.add(comment);
				ContainerController.saveCommentList();
				
				String id = request.getSession().getAttribute("apartmentId").toString();
				Apartment apartment = ContainerController.findApartmentById(Integer.parseInt(id));
				Role role = Role.valueOf(request.getSession().getAttribute("role").toString());
				Integer userId = Integer.parseInt(request.getSession().getAttribute("id").toString());
				ArrayList<Comment> comments = new ArrayList<Comment>(); 
				String permission = "false";
				if(role == Role.GUEST) {
					comments = ContainerController.findCommentsByApartmentIdAndHidden(apartment.getId(), false);
					ArrayList<Reservation> reservations = ContainerController.findReservationsByApartmentIdAndGuestId(apartment.getId(), userId);
					for(Reservation reservation : reservations) {
						if(reservation.getStatus() == ReservationStatus.REJECTED || reservation.getStatus() == ReservationStatus.FINISHED) {
							permission = "true";
							break;
						}
					}
				} else {
					comments = ContainerController.findCommentsByApartmentId(apartment.getId());
					permission = "true";
				}
				ServletController.putApartmentInSession(apartment, request.getSession());
				ServletController.putCommentListInSession(comments, request.getSession());
				ServletController.putCommentPermissionInSession(permission, request.getSession());
				ServletController.forwardToViewComments(request, response);
			} else {
				ServletController.sendBadRequest(response, MessageGenerator.generateSpammingMessage());
			}
		} else {
		    ServletController.sendBadRequest(response, validationResponse.getErrorMessage());
		}
	}
	
}
