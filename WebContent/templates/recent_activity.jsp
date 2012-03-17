<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="models.Quiz" %>
<%@ page import="models.QuizResult" %>
<%@ page import="java.util.ArrayList" %>
<h3>Your Recent Scores</h3>
<table>
	<thead>
		<tr><th>Quiz</th><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% ArrayList<QuizResult> quizResultsByDate = (ArrayList<QuizResult>) request.getAttribute("recentScores");
			for(int i = 0; i < quizResultsByDate.size(); i++) { 
			QuizResult r = quizResultsByDate.get(i);
		%>
			 <tr><td><a href="QuizPage?id=<%= r.getQuizID() %>"><%= r.getQuiz().getName() %></a></td>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Your Recently Created Quizzes</h3>
<table>
	<thead>
		<tr><th>Quiz</th><th>Date Created</th></tr>
	</thead>
	<tbody>
		<% ArrayList<Quiz> usersQuizzes = (ArrayList<Quiz>) request.getAttribute("usersNewQuizzes");
			for(int i = 0; i < usersQuizzes.size() && i < 5; i++) { 
				Quiz r = usersQuizzes.get(i);
		%>
			 <tr><td><a href="QuizPage?id=<%= r.getId() %>"><%= r.getName() %></a></td>
			 <td><%= r.getDateCreated() %></td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Friends Recently Created Quizzes</h3>
<table>
	<thead>
		<tr><th>Quiz</th><th>Date Created</th></tr>
	</thead>
	<tbody>
		<% ArrayList<Quiz> friendsNewQuizzes = (ArrayList<Quiz>) request.getAttribute("friendsNewQuizzes");
			for(int i = 0; i < friendsNewQuizzes.size() && i < 5; i++) { 
				Quiz r = friendsNewQuizzes.get(i);
		%>
			 <tr><td><a href="QuizPage?id=<%= r.getId() %>"><%= r.getName() %></a></td>
			 <td><%= r.getDateCreated() %></td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Friends Recent Scores</h3>
<table>
	<thead>
		<tr><th>Quiz</th><th>Score</th><th>Date</th><th>Time Taken</th></tr>
	</thead>
	<tbody>
		<% ArrayList<QuizResult> friendsQuizResultsByDate = (ArrayList<QuizResult>) request.getAttribute("friendsRecentScores");
			for(int i = 0; i < friendsQuizResultsByDate.size(); i++) { 
			QuizResult r = friendsQuizResultsByDate.get(i);
		%>
			 <tr><td><a href="QuizPage?id=<%= r.getQuizID() %>"><%= r.getQuiz().getName() %></a></td>
			 <td><%= r.getScore() %></td>
			 <td><%= r.getDateTaken() %></td> 
			 <td><%= r.getCompletionTime() %> seconds</td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>All Recently Created Quizzes</h3>
<table>
	<thead>
		<tr><th>Quiz</th><th>Date Created</th></tr>
	</thead>
	<tbody>
		<% ArrayList<Quiz> newQuizzes = (ArrayList<Quiz>) request.getAttribute("newQuizzes");
			for(int i = 0; i < newQuizzes.size() && i < 5; i++) { 
				Quiz r = newQuizzes.get(i);
		%>
			 <tr><td><a href="QuizPage?id=<%= r.getId() %>"><%= r.getName() %></a></td>
			 <td><%= r.getDateCreated() %></td>
			 </tr>
		<% } %>
	</tbody>
</table>

<h3>Popular Quizzes</h3>
<ul class = "mail">
	<% ArrayList<Quiz> popularQuizzes = (ArrayList<Quiz>)request.getAttribute("popularQuizzes");
		for(int i = 0; i < popularQuizzes.size(); i++) {
			out.println("<li><a href='QuizPage?id=" + popularQuizzes.get(i).getId() + "'>"  + popularQuizzes.get(i).getName() + "</a></li>");
		}
	%>
</ul>