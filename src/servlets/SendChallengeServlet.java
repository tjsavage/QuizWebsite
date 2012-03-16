package servlets;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Challenge;
import models.ChallengeFactory;
import models.Quiz;
import models.QuizFactory;
import models.UserFactory;
import models.User;

/**
 * Servlet implementation class SendChallengeServlet
 */
@WebServlet("/SendChallengeServlet")
public class SendChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("LoginServlet");
		}
		
		UserFactory uf = new UserFactory();
		ArrayList<User> friends = uf.getFriends(user.getID());
		request.setAttribute("friends", friends);
		QuizFactory qf = new QuizFactory();
		ArrayList<Quiz> quizzes = qf.retrieveAllQuizzes();
		request.setAttribute("quizzes", quizzes);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("SendChallenge.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int otherID = Integer.parseInt(request.getParameter("otherID"));
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		User user = (User) request.getSession().getAttribute("user"); 
		
		Challenge challenge = new Challenge();
		challenge.setCompleted(0);
		challenge.setFromID(user.getID());
		challenge.setToID(otherID);
		challenge.setQuizID(quizID);
		ChallengeFactory cf = new ChallengeFactory();
		cf.sendChallenge(challenge);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
