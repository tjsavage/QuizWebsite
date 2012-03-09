<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
%>
<title>Friend Search</title>
</head>
<body>
	<h1> This. Is. The. QUIZ NETWORK. </h1>
		
		<p> Find friends: </p>
		<ul>
		<%
			ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
			for (int i = 0; i < users.size(); i++) {
				out.println("<li> <a href = \"OtherUserServlet?id=" + users.get(i).getID() + "\">" + users.get(i).getUsername() + "</a> </li>");
			}
		%>
		
		</ul>
		<%
			out.print("<a href = \"UserPageServlet\">" + user.getUsername() + "</a>");
		%>
</body>
</html>