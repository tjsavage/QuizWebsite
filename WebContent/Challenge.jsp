<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Challenge" %>
<%@ page import="models.User" %>
<%@ page import="models.UserFactory" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Challenges</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
<p> challenges: </p>
	<%
		ArrayList<Challenge> challenges = (ArrayList<Challenge>) request.getAttribute("challenges");
		for (int i = 0; i < challenges.size(); i++) {
			UserFactory uf = new UserFactory();
			String username = uf.getUserFromID(challenges.get(i).getFromID()).getUsername();
			out.println("<li><a href='OtherUserServlet?id=" + challenges.get(i).getFromID() + "'>" + username + "</a> challenges you to beat their score of " + challenges.get(i).getBestScore() + " on " + "<a href='QuizPage?id=" + challenges.get(i).getQuizID() + "' >" + challenges.get(i).getQuizName() + "</a> </li>");
		}
	%>
	<form method = "get" action = "SendChallengeServlet" >
			<p> 
				<input type = "submit" value= "Challenge a friend">
			</p>
	</form>
</div>
</div>
</body>
</html>