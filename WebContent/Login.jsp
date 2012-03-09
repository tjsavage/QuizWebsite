<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Jack and taylor's Website</h1>
		<p> 
		<%
		Boolean isIncorrect = (Boolean) request.getAttribute("isIncorrect");
		if (isIncorrect) out.println("Your information was incorrect.");
		else out.println("Please log in.");
		%>
		</p> 
		<form method = "post" action = "LoginServlet" >
			<p> 
				User Name: <input type="text" name=username>
				<br>
				Password: <input type="text" name=password>
				<input type = "submit" value= "Login">
			</p>
		</form>
		<a href = "RegisterServlet"> Create a new account here.</a>
</body>
</html>