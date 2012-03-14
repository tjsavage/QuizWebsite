<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Quizzes</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
<h1>All Quizzes</h1>
<table>
<% ArrayList<Quiz> quizzes = (ArrayList<Quiz>)request.getAttribute("quizzes");
	for(Quiz currQuiz : quizzes) {
		out.println("<tr><td><a href='QuizPage?id=" + currQuiz.getId() + "'>" + currQuiz.getName() + "</a></td></tr>");
	}
%>
</table>
</div>
</div>
</body>
</html>