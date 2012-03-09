<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	User other = (User) request.getAttribute("other");
	session.setAttribute("other", other);
%>
<title> <% out.print(other.getUsername() + "'s page"); %></title>
</head>
<body>
	<h1> <% out.print(other.getUsername() + "'s page"); %> </h1>
		<p> 
			<% 
				if ((Boolean) request.getAttribute("isWaiting")) out.print("Your friend request is pending.");
				else if ((Boolean) request.getAttribute("isFriend")) out.print("You're friends with " + other.getUsername());
				else out.print("You are not friends with " + other.getUsername());
			%>
		</p>
		<form method = "post" action = "FriendSearchServlet" >
			<p> 
				<input type = "submit" value= "Add <%= other.getUsername() %> " >
			</p>
		</form>
	<%
		out.print("<a href = \"UserPageServlet\">" + user.getUsername() + "</a>");
	%>
</body>
</html>