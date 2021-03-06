<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Quiz" %>
<%@ page import="models.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<% Quiz quiz = (Quiz)request.getAttribute("quiz"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= quiz.getName() %></title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
<div class="wrapper">
<h1><%= quiz.getName() %></h1>
<form method="POST">
<input type="hidden" name="start_time" value="<%= new Date().getTime() %>" />
<% 
ArrayList<Question> questions = quiz.getQuestions();
for(int index = 0; index < questions.size(); index++) { 
	Question question = questions.get(index); 
	%>
	<%@ include file="QuestionTemplate.jsp" %>
<% } %>
<input type="submit" value="Submit" />

</form>
</div>
</div>
</body>
</html>