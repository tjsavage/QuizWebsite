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

<script type="text/javascript" src="/QuizWebsite/js/jquery.js"></script>
<script type="text/javascript" src="/QuizWebsite/js/jquery-ui.js"></script>
<script type="text/javascript" src="js/singlePageQuiz.js"></script>
</head>
<body>
<h1><%= quiz.getName() %></h1>
<form method="POST">
<input type="hidden" name="start_time" value="<%= new Date().getTime() %>" />
<% 
ArrayList<Question> questions = quiz.getQuestions();
for(int index = 0; index < questions.size(); index++) { 
	Question question = questions.get(index); 
	%>
	<div class="question_block" <% if (index > 0) { %>style="display:none"<% } %> id="question_<%= index %>">
	<%@ include file="QuestionTemplate.jsp" %>
	<% if (index < questions.size() - 1)  { %>
		<a href="javascript:showQuestion(<%= (index + 1) %>)">Next Question</a>
	<% } else { %>
		<input type="submit" value="Submit" />
	<% } %>
	</div>
<% } %>


</form>
</body>
</html>