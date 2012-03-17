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
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/FriendsServlet")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null || !user.isLoggedIn()) {
			response.sendRedirect("LoginServlet");
			return;
		}
		int id = user.getID();
		
		UserFactory uf = UserFactory.sharedInstance();
		
		ArrayList<User> pendingFriends = uf.getPendingFriendRequests(id);
		ArrayList<User> friendRequests = uf.getFriendRequests(id);
		ArrayList<User> friends = uf.getFriends(id);
		
		request.setAttribute("pendingFriends", pendingFriends);
		request.setAttribute("friendRequests", friendRequests);
		request.setAttribute("friends", friends);
		
		RequestDispatcher dispatch;
		dispatch = request.getRequestDispatcher("Friends.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
