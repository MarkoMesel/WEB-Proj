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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> users = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public void init() {
    		/* Koristicemo ServletContext - sigurni repozitorijum, kojem mogu da pristupe svi korisnici nase aplikacije
    		   to je deljeni repozitorijum i pristup njemu je sinhornizovan
    		   mozemo ga preuzeti u bilo kojoj metodi Servlet-a (doGet, doPost, init, itd.)
    		   ima metodu setAttribute(key, value), pomocu koje u njega pod nekim kljucem smestate podatke
    		   ovde su nam znacajni podaci o korisnicima, pa smestamo users listu pod kljucem users. 
    		   Kljuc je proizvoljan (ne mora da bude isti kao naziv objekta koji smestate u context).
    		   
    		   Ukoliko smo pod istim kljucem imali vec u nekom servletu podesen neki objekat, potrebno je da ga samo preuzmemo, a ne da ga pregazimo.
    		   *
    		   */
    		ServletContext servletContext = this.getServletContext();
    		if (servletContext.getAttribute("users") == null) {
        		this.getServletContext().setAttribute("users", users);
    		} else {
    			users = (List<String>) servletContext.getAttribute("users");
    		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("ime");
		users.add(user);
		
		PrintWriter out = response.getWriter();
		out.append("<html><body><table border=\"1\">");
		for(String oneUser : users) {
			out.append("<tr><td>" + oneUser + "</td><td>" + getUserRemoveLink(oneUser) + "</td></tr>");
		}
		out.append("</table>");
		out.append(getLinks());
		out.append("</body></html>");
	}
	
	private String getLinks() {
		String links = "";
		links += "<a href=\"/HelloWorld/registration.html\">Registracija korisnika</a><br/>";
		links += "<a href=\"/HelloWorld\">Pocetna</a>";
		return links;
	}
	
	private String getUserRemoveLink(String user) {
		return "<a href=\"/HelloWorld/RemoveUserServlet?ime=" + user + "\">Ukloni</a>";
	}

}
