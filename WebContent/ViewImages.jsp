<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Image" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<%
	User user = (User) request.getSession().getAttribute("user");
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Images</title>
</head>
<body>
	<h1> Look at this fucking guy: </h1>
		<%
			ArrayList<Image> images = (ArrayList<Image>) request.getAttribute("images");
			for (int i = 0; i < images.size(); i++) {
				System.out.print(images.get(i).getUrl());
				out.print("<img src=\"" + images.get(i).getUrl() + "\"/>");
				if (user.getID() == images.get(i).getUserID()) {
					out.print("<form method = \"get\" action = \"ProfilePictureServlet\" >");
					out.print("<input type = \"hidden\" name = \"imageID\" value = \"" + images.get(i).getID() + "\" >");
					out.print("<input type = \"submit\" value= \"prof pic plzzz!\"></form>");
					out.print("<form method = \"get\" action = \"DeletePictureServlet\" >");
					out.print("<input type = \"hidden\" name = \"imageID\" value = \"" + images.get(i).getID() + "\" >");
					out.print("<input type = \"submit\" value= \"not so hot\"></form>");
				}
			}
		%>
		<form method = "get" action = "UserPageServlet" >
			<p> 
				<input type = "submit" value= "I can't stand it">
			</p>
		</form>
</body>
</html>