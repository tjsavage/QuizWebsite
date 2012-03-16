<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Quiz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User other = (User) request.getAttribute("other");
	Quiz quiz = (Quiz) request.getAttribute("quiz");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Terminate User</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1> Do you really want to delete <%= quiz.getName() %> ? </h1>
	<form method = "post" action = "RemoveQuizServlet" >
		Send a message to the quiz's creator: <BR>
		<TEXTAREA NAME="reason" COLS=40 ROWS=6></TEXTAREA> Your quiz has been removed.
		<input type = "hidden" name = "quizID" value = "<%= quiz.getId() %>" >
		<input type = "hidden" name = "otherID" value = "<%= other.getID() %>" >
		<P><INPUT TYPE="SUBMIT" VALUE="remove">
	</form>
</div>
</div>
</body>
</html>