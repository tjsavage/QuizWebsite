package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import models.MessageFactory;
import models.User;
import models.UserFactory;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		int id = Integer.parseInt(request.getParameter("id"));
		UserFactory uf = new UserFactory();
		User other = uf.getUserFromID(id);
		request.setAttribute("other", other);
		dispatch = request.getRequestDispatcher("Message.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		MessageFactory mf = new MessageFactory();
		Message message = new Message();
		message.setToID(Integer.parseInt(request.getParameter("id")));
		message.setFromID(user.getID());
		message.setMessage(request.getParameter("message"));
		message.setRead(false);
		mf.sendMessage(message);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
