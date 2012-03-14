<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User user = (User) request.getSession().getAttribute("user");
	User other = (User) request.getAttribute("other");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Page</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1> Tell <%= other.getUsername() %> what you really think. </h1>
	<form method = "post" action = "MessageServlet" >
		your message:<BR>
		<TEXTAREA NAME="message" COLS=40 ROWS=6></TEXTAREA>
		<input type = "hidden" name = "id" value = "<%= other.getID() %>" >
		<P><INPUT TYPE="SUBMIT" VALUE="send">
	</form>
</div>
</div>
</body>
</html>