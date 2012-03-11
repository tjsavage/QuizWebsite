<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
	Message message = (Message) request.getAttribute("message");
	User other = (User)	request.getAttribute("other");
	User user = (User)	request.getSession().getAttribute("user");
	boolean isInbox = (Boolean) request.getAttribute("isInbox");
%>
<title>Readin N' Stuff</title>
</head>
<body>
	<h1>
	<%
		if (isInbox) out.print("Sent from " + other.getUsername() + ":");
		else out.print("Sent to " + other.getUsername() + ":");
	%>
	</h1>
		<p> 
			<% 
				out.print(message.getMessage());
			%>
		</p>
		<p> 
			<% 
			if (isInbox) out.print("<a href = \"MessageServlet?id=" + other.getID() + "\"> holla back. </a> <br>");
			else out.print("<a href = \"MessageServlet?id=" + other.getID() + "\"> send 'em another. </a> <br>");
			out.print("<a href = \"UserPageServlet\"> I want to go home. </a>");
			%>
		</p>
</body>
</html>