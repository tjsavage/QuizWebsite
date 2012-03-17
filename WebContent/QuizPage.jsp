<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="models.QuizResult" %>
<%@ page import="java.util.ArrayList" %>

<% Quiz quiz = (Quiz)request.getAttribute("quiz"); 
	ArrayList<QuizResult> quizResultsByScore = (ArrayList<QuizResult>)request.getAttribute("quizResultsByScore");
	ArrayList<QuizResult> quizResultsByDate = (ArrayList<QuizResult>)request.getAttribute("quizResultsByDate");
	ArrayList<QuizResult> quizResultsForUser = (ArrayList<QuizResult>)request.getAttribute("quizResultsForUser");
	ArrayList<QuizResult> quizResultsToday = (ArrayList<QuizResult>)request.getAttribute("quizResultsToday");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= quiz != null? quiz.getName() : "Invalid Quiz"  %></title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
<% if (quiz == null) { %>
<h1>Invalid Quiz</h1>
<% } else { %>
<h1><%= quiz.getName() %><small>Created by <a href="UserPageServlet?id=<%= quiz.getCreatorID() %>"><%= quiz.getCreator().getUsername() %></a></small></h1>
<p><%= quiz.getDescription() %></p>
		<% 
				if ((Boolean) request.getAttribute("isAdmin")) {
					out.print("<form method = \"get\" action = \"RemoveQuizServlet\" >");
					out.print("<input type = \"hidden\" name = \"id\" value = '" + quiz.getId()  + "'>");
					out.print("<input type = \"submit\" value= \"delete this quiz\" ></form><br>");
					out.print("<form method = \"get\" action = \"ClearQuizServlet\" >");
					out.print("<input type = \"hidden\" name = \"quizID\" value = '" + quiz.getId()  + "'>");
					out.print("<input type = \"submit\" value= \"clear this quiz's history\" ></form>");
				}
		%>
<br>
<h3>Average Score - <%= request.getAttribute("averageScore") %></h3>
<h3>Top Scores - All Time</h3>
<table>
	<thead>
		<tr><th>User</th><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% for(int i = 0; i < quizResultsByScore.size(); i++) { 
			QuizResult r = quizResultsByScore.get(i);
		%>
			 <tr><td><a href="UserPageServlet?id=<%= r.getUserID() %>"><%= r.getUser().getUsername() %></a></td>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Top Scores - Today</h3>
<table>
	<thead>
		<tr><th>User</th><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% for(int i = 0; i < quizResultsToday.size(); i++) { 
			QuizResult r = quizResultsToday.get(i);
		%>
			 <tr><td><a href="UserPageServlet?id=<%= r.getUserID() %>"><%= r.getUser().getUsername() %></a></td>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Recent Scores</h3>
<table>
	<thead>
		<tr><th>User</th><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% for(int i = 0; i < quizResultsByDate.size(); i++) { 
			QuizResult r = quizResultsByDate.get(i);
		%>
			 <tr><td><a href="UserPageServlet?id=<%= r.getUserID() %>"><%= r.getUser().getUsername() %></a></td>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Your Recent Scores</h3>
<table>
	<thead>
		<tr><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% for(int i = 0; i < quizResultsForUser.size(); i++) { 
			QuizResult r = quizResultsForUser.get(i);
		%>
			 <tr>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>
<br />
<a href="TakeQuizServlet?id=<%= quiz.getId() %>&question=0">Take Quiz</a>
<% } %>

</div>
</div>
</body>
</html>