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
 * Servlet implementation class TerminateUserServlet
 */
@WebServlet("/TerminateUserServlet")
public class TerminateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TerminateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		int otherID = Integer.parseInt(request.getParameter("id"));
		UserFactory uf = new UserFactory();
		User other = uf.getUserFromID(otherID);
		request.setAttribute("other", other);
		dispatch = request.getRequestDispatcher("TerminateUser.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(!user.isLoggedIn()) response.sendRedirect("LoginServlet");
		int otherID = Integer.parseInt(request.getParameter("id"));
		UserFactory uf = new UserFactory();
		User other = uf.getUserFromID(otherID);
		String reason = (String) request.getParameter("reason");
		AdminControl ac = AdminControl.sharedInstance();
		ac.removeUser(other.getID(), user.getID(), other.getUsername(), reason);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
