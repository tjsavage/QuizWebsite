package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdminControl;
import models.Message;
import models.MessageFactory;
import models.Quiz;
import models.QuizFactory;
import models.User;
import models.UserFactory;

/**
 * Servlet implementation class RemoveQuizServlet
 */
@WebServlet("/RemoveQuizServlet")
public class RemoveQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		int quizID = Integer.parseInt(request.getParameter("id"));
		QuizFactory qf = new QuizFactory();
		Quiz quiz = qf.retrieveQuiz(quizID);
		User other = quiz.getCreator();
		request.setAttribute("quiz", quiz);
		request.setAttribute("other", other);
		dispatch = request.getRequestDispatcher("RemoveQuiz.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (!user.isLoggedIn()) response.sendRedirect("LoginServlet");
		int otherID = Integer.parseInt(request.getParameter("otherID"));
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		String reason = request.getParameter("reason");
		AdminControl ac = AdminControl.sharedInstance();
		MessageFactory mf = new MessageFactory();
		Message message = new Message();
		message.setFromID(user.getID());
		message.setToID(otherID);
		message.setMessage(reason);
		message.setRead(false);
		mf.sendMessage(message);
		ac.removeQuiz(quizID);
		response.sendRedirect("/QuizWebsite/UserPageServlet");
	}

}
