package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quiz;
import models.QuizFactory;

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
			int questionIndex = Integer.parseInt(request.getParameter("question"));
			request.setAttribute("question", quiz.getQuestion(questionIndex));
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
		// TODO Auto-generated method stub
	}

}
