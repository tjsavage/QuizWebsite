<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="HeaderInclude.jsp" />
</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
<h1>Register to Jack and taylor's Website</h1>
		<p>
		<%
		Boolean isInUse = (Boolean) request.getAttribute("isInUse");
		if (isInUse) out.println("That username is already in use.");
		else out.println("Please enter a username and password.");
		%>
		</p> 
		<form method = "post" action = "RegisterServlet" >
			<p> 
				User Name: <input type="text" name=username>
				<br>
				Password: <input type="password" name=password>
				<input type = "submit" value= "Register">
			</p>
		</form>
		<a href = "LoginServlet"> Already have an account? Login here.</a>
</div>
</div>
</body>
</html>