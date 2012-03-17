<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Challenge" %>
<%@ page import="models.User" %>
<%@ page import="models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send A Challenge</title>
<jsp:include page="HeaderInclude.jsp" />

</head>
<body class = "challenge">
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1>Challenge them!</h1>
	<form method = "post" action= "SendChallengeServlet"> 
		<div>
			Select a friend to send this to:
			<select name = "otherID">
				<%
					ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
					for (int i = 0; i < friends.size(); i++) {
						out.print("<option value = \"" + friends.get(i).getID() + "\">" + friends.get(i).getUsername() + "</option>");
					}
				%>
			</select>
		</div>
		<div>
			Select a quiz to challenge them with:
			<select name = "id">
				<%
					ArrayList<Quiz> quizzes = (ArrayList<Quiz>) request.getAttribute("quizzes");
					for (int i = 0; i < quizzes.size(); i++) {
						out.print("<option value = \"" + quizzes.get(i).getId() + "\">" + quizzes.get(i).getName() + "</option>");
					}
				%>
			</select>
		</div>
		<input type = "submit" value= "let's do this!">
	</form>
	</div></div>
</body>
</html>



