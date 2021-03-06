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
import models.Image;
import models.ImageFactory;
import models.Message;
import models.MessageFactory;
import models.Quiz;
import models.QuizFactory;
import models.QuizResult;
import models.QuizResultFactory;
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
		if (user == null || !user.isLoggedIn()) {
			response.sendRedirect("LoginServlet");
			return;
		}
		
		UserFactory uf = new UserFactory();
		MessageFactory mf = new MessageFactory();
		AnouncementFactory af = new AnouncementFactory();
		ImageFactory imgf =ImageFactory.sharedInstance();
		QuizFactory qf = QuizFactory.sharedInstance();
		QuizResultFactory qrf = QuizResultFactory.sharedInstance();
		
		int id = user.getID();
		
		Image profileImage = imgf.getProfileImage(user.getID());
		ArrayList<Message> inbox = mf.getInbox(id);
		ArrayList<Message> sent = mf.getSent(id);
		ArrayList<Anouncement> anouncements = af.getFiveMostRecentAdmin();
		ArrayList<Quiz> popularQuizzes = qf.retrievePopularQuizzes();
		ArrayList<Quiz> newQuizzes = qf.retrieveNewQuizzes();
		ArrayList<QuizResult> recentScores = qrf.retrieveQuizResultsForUser(id);
		ArrayList<Quiz> usersNewQuizzes = qf.retrieveRecentlyCreatedQuizzesByUser(id);
		ArrayList<Quiz> friendsNewQuizzes = qf.retrieveFriendsQuizzes(id);
		ArrayList<QuizResult> friendsRecentScores = qrf.retrieveFriendsQuizResults(id);
		
		ArrayList<User> friends = uf.getFriends(user.getID());
		
		request.setAttribute("friends", friends);
		request.setAttribute("friendsNewQuizzes", friendsNewQuizzes);
		request.setAttribute("friendsRecentScores", friendsRecentScores);
		request.setAttribute("popularQuizzes", popularQuizzes);
		request.setAttribute("newQuizzes", newQuizzes);
		request.setAttribute("recentScores", recentScores);
		request.setAttribute("usersNewQuizzes", usersNewQuizzes);
		request.setAttribute("profileImage", profileImage);
		request.setAttribute("anouncements", anouncements);
		request.setAttribute("inbox", inbox);
		request.setAttribute("sent", sent);
		
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
