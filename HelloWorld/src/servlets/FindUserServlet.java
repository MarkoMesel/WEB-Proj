package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindUserServlet
 */
@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> users = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public void init() {
    		ServletContext servletContext = this.getServletContext();
		if (servletContext.getAttribute("users") == null) {
    			this.getServletContext().setAttribute("users", users);
		} else {
			users = (List<String>) servletContext.getAttribute("users");
		}
		/* Opet preuzimamo ServletContext, na isti nacin kao i u init metodi u RegistrationServletu.
		 * U njemu bi sada trebalo da se nalazi lista nasih korisnika.
		 * Iskoristicemo metodu getAttribute(key) da preuzmemo objekat koji je u context sacuvan pod 
		 * nekim kljucem. Ovde znamo da smo korisnike sacuvali pod kljucem "users", pa je tako mozemo
		 * pod tim kljucem i preuzeti.
		 * Kastovanje je neophodno, jer se u ServletContext mogu staviti bilo kakvi objekti (naslednici klase Object).
		 * Ako znamo da smo pod nekim kljucem stavili list, treba ono sto preuzmemo pod tim kljucem da kastujemo u listu.*/
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("ime");
		
		PrintWriter out = response.getWriter();
		String message = "";
		if (users.contains(user)) {
			message = "Korisnik " + user + " pronadjen.";
		} else {
			message = "Korisnik " + user + " nije pronadjen.";
		}
		out.append("<html><body><h3>" + message + "</h3>");
		out.append(getLinks());
		out.append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getLinks() {
		String links = "";
		links += "<a href=\"/HelloWorld\">Pocetna</a>";
		return links;
	}

}
