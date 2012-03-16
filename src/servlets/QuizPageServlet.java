package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quiz;
import models.QuizFactory;
import models.QuizResult;
import models.QuizResultFactory;
import models.User;

/**
 * Servlet implementation class QuizPage
 */
@WebServlet("/QuizPage")
public class QuizPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizID = Integer.parseInt(request.getParameter("id"));
		QuizFactory factory = QuizFactory.sharedInstance();
		Quiz quiz = factory.retrieveQuiz(quizID);
		User user = (User)request.getSession().getAttribute("user");
		
		QuizResultFactory resultFactory = QuizResultFactory.sharedInstance();
		ArrayList<QuizResult> quizResultsByDate = resultFactory.retrieveSortedQuizResultsForQuiz(quizID, QuizResultFactory.SortingMethod.DATE);
		ArrayList<QuizResult> quizResultsByScore = resultFactory.retrieveSortedQuizResultsForQuiz(quizID, QuizResultFactory.SortingMethod.SCORE);
		ArrayList<QuizResult> quizResultsForUser = resultFactory.retrieveQuizResultsForUser(user.getID());
		
		request.setAttribute("quiz", quiz);
		request.setAttribute("quizResultsByDate", quizResultsByDate);
		request.setAttribute("quizResultsByScore", quizResultsByScore);
		request.setAttribute("quizResultsForUser", quizResultsForUser);
		request.setAttribute("isAdmin", user.getAdmin());
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizPage.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
