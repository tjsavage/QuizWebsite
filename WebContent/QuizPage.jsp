<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="java.util.ArrayList" %>

<% Quiz quiz = (Quiz)request.getAttribute("quiz"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= quiz != null? quiz.getName() : "Invalid Quiz"  %></title>
</head>
<body>
<% if (quiz == null) { %>
<h1>Invalid Quiz</h1>
<% } else { %>
<h1><%= quiz.getName() %><small>Created by <a href="UserPageServlet?id=<%= quiz.getCreatorID() %>"><%= quiz.getCreator().getUsername() %></a></small></h1>
<p><%= quiz.getDescription() %></p>
<a href="TakeQuizServlet?id=<%= quiz.getId() %>">Take Quiz</a>
<% } %>
</body>
</html>