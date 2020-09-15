package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveUserServlet
 */
@WebServlet("/RemoveUserServlet")
public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("ime");
		@SuppressWarnings("unchecked")
		List<String> users = (ArrayList<String>) getServletContext().getAttribute("users");
		
		users.remove(user);
		PrintWriter out = response.getWriter();
		out.append("<html><body><table border=\"1\">");
		for(String oneUser : users) {
			out.append("<tr><td>" + oneUser + "</td><td>" + getUserRemoveLink(oneUser) + "</td></tr>");
		}
		out.append("</table>");
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
		links += "<a href=\"/HelloWorld/registration.html\">Registracija korisnika</a><br/>";
		links += "<a href=\"/HelloWorld\">Pocetna</a>";
		return links;
		
	}
	
	private String getUserRemoveLink(String user) {
		return "<a href=\"/HelloWorld/RemoveUserServlet?ime=" + user + "\">Ukloni</a>";
	}

}
