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
		<p> Please enter an new user name and password. </p> 
		<form method = "post" action = "RegisterServlet" >
			<p> 
				User Name: <input type="text" name=username>
				<br>
				Password: <input type="text" name=password>
				<input type = "submit" value= "Register">
			</p>
		</form>
		<a href = "LoginServlet"> Already have an account? Login here.</a>
</body>
</html>