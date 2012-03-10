<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User user = (User) request.getSession().getAttribute("user");
	User other = (User) request.getAttribute("other");
	session.setAttribute("other", other);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tell them what you really think.</title>
</head>
<body>
	<h1> Tell them what you really think. </h1>
	<form method = "post" action = "MessageServlet" >
		your message:<BR>
		<TEXTAREA NAME="message" COLS=40 ROWS=6></TEXTAREA>
		<P><INPUT TYPE=SUBMIT VALUE="send">
	</FORM>
</body>
</html>