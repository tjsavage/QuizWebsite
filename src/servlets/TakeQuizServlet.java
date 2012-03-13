package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Question;
import models.QuestionFactory;
import models.Quiz;
import models.QuizFactory;
import models.QuizResult;
import models.QuizResultFactory;
import models.User;

/**
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizID = Integer.parseInt(request.getParameter("id"));
		QuizFactory qf = QuizFactory.sharedInstance();
		Quiz quiz = qf.retrieveQuiz(quizID);
		request.setAttribute("quiz", quiz);
		
		if (quiz.isMultipage()) {
			RequestDispatcher dispatch = request.getRequestDispatcher("SingleQuestion.jsp");
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("SinglePageQuiz.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizID = Integer.parseInt(request.getParameter("id"));
		long startTimeMS = Long.parseLong(request.getParameter("start_time"));
		User user = (User) request.getSession().getAttribute("user");
		
		int elapsedTime = (int) (((new Date().getTime()) - startTimeMS) / 1000);
		QuizFactory qf = QuizFactory.sharedInstance();
		Quiz quiz = qf.retrieveQuiz(quizID);
		
		QuestionFactory questionFactory = QuestionFactory.sharedInstance();
		int score = 0;
		
		ArrayList<String> yourAnswers = new ArrayList<String>();
		ArrayList<String> acceptableAnswers = new ArrayList<String>();
		
		for(int i = 0; i < quiz.getQuestions().size(); i++) {
			int questionID = Integer.parseInt(request.getParameter("" + i + "_id"));
			try {
				Question q = questionFactory.retrieveQuestion(questionID);
				String answer = request.getParameter("" + i + "_answer");
				yourAnswers.add(answer);
				acceptableAnswers.add(q.getAnswerString());
				if (q.isCorrectAnswer(answer)) {
					score += 1;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		QuizResult result = new QuizResult(quizID, user.getID(), score, elapsedTime, null);
		QuizResultFactory qrf = QuizResultFactory.sharedInstance();
		qrf.insertQuizResult(result);
		
		request.setAttribute("quiz", quiz);
		request.setAttribute("quizResult", result);
		request.setAttribute("yourAnswers", yourAnswers);
		request.setAttribute("acceptableAnswers", acceptableAnswers);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizResult.jsp");
		dispatch.forward(request, response);
	}

}
