package servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserFactory;

/**
 * Servlet implementation class FriendSearchServlet
 */
@WebServlet("/FriendSearchServlet")
public class FriendSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch; 
		UserFactory uf = new UserFactory();
		User user = (User) request.getSession().getAttribute("user");
		int id = user.getID();
		ArrayList<User> users = uf.getListUsers(id);
		request.setAttribute("users", users);
		dispatch = request.getRequestDispatcher("FriendSearch.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch; 
		User other = (User) request.getSession().getAttribute("other");
		User user = (User) request.getSession().getAttribute("user");
		if (!user.isFriend(other.getID()) || !user.isWaiting(other.getID())) {
			user.sendFriendRequest(other.getID());
			response.sendRedirect("/QuizWebsite/OtherUserServlet?id=" + other.getID());
		} else {
			response.sendRedirect("/QuizWebsite/OtherUserServlet?id=" + other.getID());
		}
	}

}
