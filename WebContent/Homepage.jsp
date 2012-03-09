<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
%>
<title>Homepage</title>
</head>
<body>
	<h1>It's the homepage, stupid.</h1>
	<%
		out.print("<a href = \"UserPageServlet\">" + user.getUsername() + "</a>");
	%>
	
</body>
</html>