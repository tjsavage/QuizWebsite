package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Question;
import models.QuestionFactory;

/**
 * Servlet implementation class SingleQuestionServlet
 */
@WebServlet("/SingleQuestionServlet")
public class SingleQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionID = Integer.parseInt(request.getParameter("questionID"));
		QuestionFactory qf = QuestionFactory.sharedInstance();
		try {
			Question question = qf.retrieveQuestion(questionID);
			String answer = (String)request.getParameter("answer");
			boolean correct = question.isCorrectAnswer(answer);
			
			response.getWriter().println("{\"question\": {\"id\":" + question.getId() + ", \"answers\":\"" + question.getAnswerString() + "\", \"correct\":" + (correct ? 1 : 0) + "}}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
