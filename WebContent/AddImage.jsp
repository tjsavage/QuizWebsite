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
<title>Message Page</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1> Add an image to your profile </h1>
	<form method = "post" action = "AddImageServlet" >
		what's the image's url? <input type="text" name=url>
		<input type = "checkbox" name = "isProfile" value = "1"/> hot new profile pic?
		<P><INPUT TYPE="SUBMIT" VALUE="add this image">
	</form>
</div>
</div>
</body>
</html>