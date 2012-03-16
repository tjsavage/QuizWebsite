package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import models.User;
import models.MessageFactory;
import models.UserFactory;

/**
 * Servlet implementation class ReadMessageServlet
 */
@WebServlet("/ReadMessageServlet")
public class ReadMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MessageFactory mf = new MessageFactory();
		UserFactory uf = new UserFactory();
		Message message = mf.getMessage(id);
		User other;
		boolean isInbox;
//		System.out.print(((User)(request.getSession().getAttribute("user"))).getID());
//		System.out.print(message.getFromID());
		User user = ((User)(request.getSession().getAttribute("user")));
		if (!user.isLoggedIn()) response.sendRedirect("LoginServlet");
		if (user.getID() == message.getFromID()) {
			other = uf.getUserFromID(message.getToID());
			isInbox = false;
		} else {
			other = uf.getUserFromID(message.getFromID());
			isInbox = true;
		}
		request.setAttribute("message", message);
		request.setAttribute("other", other);
		request.setAttribute("isInbox", isInbox);
		RequestDispatcher dispatch = request.getRequestDispatcher("ReadMessage.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
