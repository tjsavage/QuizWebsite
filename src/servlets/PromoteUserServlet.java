package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdminControl;
import models.Message;
import models.MessageFactory;
import models.User;
import models.UserFactory;

/**
 * Servlet implementation class PromoteUserServlet
 */
@WebServlet("/PromoteUserServlet")
public class PromoteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoteUserServlet() {
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
		dispatch = request.getRequestDispatcher("PromoteUser.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (!user.isLoggedIn()) response.sendRedirect("LoginServlet");
		int otherID = Integer.parseInt(request.getParameter("id"));
		UserFactory uf = new UserFactory();
		User other = uf.getUserFromID(otherID);
		
		String messageContent = (String) request.getParameter("message");
		AdminControl ac = AdminControl.sharedInstance();
		ac.promoteUser(other.getID());
		
		MessageFactory mf = new MessageFactory();
		Message message = new Message();
		message.setToID(other.getID());
		message.setFromID(user.getID());
		message.setMessage(messageContent);
		message.setRead(false);
		
		mf.sendMessage(message);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
