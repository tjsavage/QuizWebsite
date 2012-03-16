<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User user = (User) request.getSession().getAttribute("user");
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
	<h1> Hear ye, hear ye! </h1>
	<p> inform the people: </p>
	<form method = "post" action = "SendAnouncementServlet" >
		<TEXTAREA NAME="anouncement" COLS=40 ROWS=6></TEXTAREA>
		<P><INPUT TYPE="SUBMIT" VALUE="send anouncement">
		<input type = "checkbox" name = "isAdminOnly" value = "1"/> This is for administrators only
	</form>
</div>
</div>
</body>
</html>