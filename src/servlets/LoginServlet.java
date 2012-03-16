package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserAuthentication;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
		request.setAttribute("isIncorrect", false);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserAuthentication userAuthentication = new UserAuthentication();
		RequestDispatcher dispatch;
		User user = userAuthentication.Authenticate(username, password);
		if(user != null && !user.isLoggedIn()) {
			user.setLoggedIn(true);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/QuizWebsite/HomepageServlet");
		} else {
			request.setAttribute("isIncorrect", true);
			dispatch = request.getRequestDispatcher("Login.jsp");
			dispatch.forward(request, response);
		}
		
	}

}
