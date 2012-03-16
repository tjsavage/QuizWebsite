package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdminControl;
import models.User;
import models.UserFactory;

/**
 * Servlet implementation class SendAnouncementServlet
 */
@WebServlet("/SendAnouncementServlet")
public class SendAnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAnouncementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		dispatch = request.getRequestDispatcher("SendAnouncement.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (!user.isLoggedIn()) {
			response.sendRedirect("LoginServlet");
		}
		
		String anouncement = (String) request.getParameter("anouncement");
		boolean isAdminOnly = false;
		if (request.getParameter("isAdminOnly") != null) isAdminOnly = true;
		AdminControl ac = AdminControl.sharedInstance();
		ac.sendAnouncement(user.getID(), anouncement, isAdminOnly);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
