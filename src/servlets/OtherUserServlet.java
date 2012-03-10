package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserFactory;

/**
 * Servlet implementation class OtherUserServlet
 */
@WebServlet("/OtherUserServlet")
public class OtherUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherUserServlet() {
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
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("isFriend", user.isFriend(other.getID()));
		request.setAttribute("isWaiting", user.isWaiting(other.getID()));
		request.setAttribute("other", other);
		dispatch = request.getRequestDispatcher("OtherUser.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
