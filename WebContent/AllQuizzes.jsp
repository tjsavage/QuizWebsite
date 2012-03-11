<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Quizzes</title>
</head>
<body>
<h1>All Quizzes</h1>
<table>
<% ArrayList<Quiz> quizzes = (ArrayList<Quiz>)request.getAttribute("quizzes");
	for(Quiz currQuiz : quizzes) {
		out.println("<tr><td><a href='QuizPage?id=" + currQuiz.getId() + "'>" + currQuiz.getName() + "</a></td></tr>");
	}
%>
</table>
</body>
</html>