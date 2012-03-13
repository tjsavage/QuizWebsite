<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="models.Quiz" %>
<%@ page import="models.QuizResult" %>
<%@ page import="java.util.ArrayList" %>

<% Quiz quiz = (Quiz)request.getAttribute("quiz");
	QuizResult result = (QuizResult)request.getAttribute("quizResult");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results for <%= quiz.getName() %></title>
</head>
<body>
<h1>Results for <%= quiz.getName() %></h1>
<p>You got <%= result.getScore() %> out of <%= quiz.getQuestions().size() %></p>
</body>
</html>