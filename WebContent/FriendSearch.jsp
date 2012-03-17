<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.ImageFactory" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	ImageFactory imgf = ImageFactory.sharedInstance();
%>
<title>Friend Search</title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body class ="friend">
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1> This. Is. The. QUIZ NETWORK. </h1>
		<br>
		<!--<h2> Find friends: </h2>-->
		<ul class = "menu">
		<%
			ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
			for (int i = 0; i < users.size(); i++) {
				out.println("<li> <a href = \"OtherUserServlet?id=" 
						+ users.get(i).getID() + "\">" + users.get(i).getUsername() 
							+ "</a><br><img src=\"" + imgf.getProfileImage(users.get(i).getID()).getUrl() + "\" width = \"40\"/></li>");
				
			}
		%>
		
		</ul>
</div>
</div>
</body>
</html>