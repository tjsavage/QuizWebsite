package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Anouncement;
import models.AnouncementFactory;
import models.Message;
import models.MessageFactory;
import models.User;
import models.UserFactory;

/**
 * Servlet implementation class AdminUserServlet
 */
@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		UserFactory uf = new UserFactory();
		MessageFactory mf = new MessageFactory();
		AnouncementFactory af = new AnouncementFactory();
		
		int id = user.getID();
		ArrayList<User> pendingFriends = uf.getPendingFriendRequests(id);
		ArrayList<User> friendRequests = uf.getFriendRequests(id);
		ArrayList<User> friends = uf.getFriends(id);
		ArrayList<Message> inbox = mf.getInbox(id);
		ArrayList<Message> sent = mf.getSent(id);
		ArrayList<Anouncement> anouncements = af.getFiveMostRecentAdmin();
		
		request.setAttribute("anouncements", anouncements);
		request.setAttribute("inbox", inbox);
		request.setAttribute("sent", sent);
		request.setAttribute("pendingFriends", pendingFriends);
		request.setAttribute("friendRequests", friendRequests);
		request.setAttribute("friends", friends);
		
		RequestDispatcher dispatch;
		dispatch = request.getRequestDispatcher("AdminUser.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
