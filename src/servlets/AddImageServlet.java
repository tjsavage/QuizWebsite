package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdminControl;
import models.Image;
import models.ImageFactory;
import models.User;

/**
 * Servlet implementation class AddImageServlet
 */
@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch;
		dispatch = request.getRequestDispatcher("AddImage.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String url = (String) request.getParameter("url");
		boolean isProfile = false;
		if (request.getParameter("isProfile") != null) isProfile = true;
		ImageFactory imgf = ImageFactory.sharedInstance();
		Image image = new Image();
		image.setUserID(user.getID());
		image.setUrl(url);
		image.setIsProfile(isProfile);
		imgf.addPicture(image);
		response.sendRedirect("/QuizWebsite/ViewImagesServlet?userID=" + user.getID());
	}

}
