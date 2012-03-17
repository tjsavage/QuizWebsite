<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Image" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
<%
	User user = (User) request.getSession().getAttribute("user");
	int id = (Integer) request.getAttribute("userID");
	ArrayList<Image> images = (ArrayList<Image>) request.getAttribute("images");
%>

<head>
<jsp:include page="HeaderInclude.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Images</title>
</head>
<body>
	<jsp:include page="templates/nav.jsp" />
	<div class="container">
	<div class="wrapper">
	<h1>Check dizz out: </h1>
		<%
			for (int i = 0; i < images.size(); i++) {
				System.out.print(images.get(i).getUrl());
				out.print("<img src=\"" + images.get(i).getUrl() + "\"width = \"175\"/>");
				if (user.getID() == images.get(i).getUserID()) {
					out.print("<form method = \"get\" action = \"ProfilePictureServlet\" >");
					out.print("<input type = \"hidden\" name = \"imageID\" value = \"" + images.get(i).getID() + "\" >");
					out.print("<input type = \"submit\" value= \"prof pic plzzz!\"></form>");
					out.print("<form method = \"get\" action = \"DeletePictureServlet\" >");
					out.print("<input type = \"hidden\" name = \"imageID\" value = \"" + images.get(i).getID() + "\" >");
					out.print("<input type = \"submit\" value= \"not so hot\"></form>");
				}
			}
		if (user.getID() == id) {
			out.print("<form method = \"get\" action = \"UserPageServlet\" >");
			out.print("<input type = \"submit\" value= \"I can't stand it\"></form>");
		} else {
			out.print("<a href = \"OtherUserServlet?id=" + id +"\" >I can't stand it</a>");
		}
		%>
	</div></div>
</body>
</html>