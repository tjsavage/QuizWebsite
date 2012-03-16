package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AnouncementFactory;
import models.Anouncement;
import models.Quiz;
import models.QuizFactory;
import models.User;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession(true).getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			response.sendRedirect("LoginServlet");
			return;
		}
		AnouncementFactory af = AnouncementFactory.sharedInstance();
		
		
		ArrayList<Anouncement> announcements = af.getFiveMostRecentAdmin();
		
		
		request.setAttribute("announcements", announcements);
		
		request.setAttribute("user", user);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("Homepage.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward 
	}

}
