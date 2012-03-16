<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Image" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	User other = (User) request.getAttribute("other");
	Image profileImage = (Image) request.getAttribute("profileImage");
	session.setAttribute("other", other);
%>
<title> <% out.print(other.getUsername() + "'s page"); %></title>

<jsp:include page="HeaderInclude.jsp" />

</head>
<body>
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
	<h1> <% out.print(other.getUsername() + "'s page"); %> </h1>
		</div>
		<form method = "get" action = "ViewImagesServlet" >
			<p> 
				<input type = "hidden" name = "userID" value = "<%= other.getID() %>" >
				<input type = "submit" value = "lets stalk">
			</p>
		</form>
		<img src="<%= profileImage.getUrl() %>" />
		<% 
				if ((Boolean) request.getAttribute("isAdmin")) {
					out.print("<form method = \"get\" action = \"TerminateUserServlet\" >");
					out.print("<input type = \"hidden\" name = \"id\" value = '" + other.getID()  + "'>");
					out.print("<input type = \"submit\" value= \"I don't like him\" ></form>");
					out.print("<form method = \"get\" action = \"PromoteUserServlet\" >");
					out.print("<input type = \"hidden\" name = \"id\" value = '" + other.getID()  + "'>");
					out.print("<input type = \"submit\" value= \"He needs a promotion\" ></form>");
				}
		%>
		<p> 
			<% 
				if ((Boolean) request.getAttribute("isWaiting")) out.print("Your friend request is pending.");
				else if ((Boolean) request.getAttribute("isFriend")) out.print("You're friends with " + other.getUsername());
				else {
					out.print("You are not friends with " + other.getUsername());
					out.print("<form method = \"post\" action = \"FriendSearchServlet\" >");
					out.print("<p> <input type = \"submit\" value= \"Add" + other.getUsername() +  "\" > </p></form>");
				}
			%>
		</p>
	<%
		out.print("<a href = \"MessageServlet?id=" + other.getID() + "\"> Send them a message. </a> <br>");
		out.print("<a href = \"UserPageServlet\"> I want to go home. </a>");
	%>
</div>
</div>
</body>
</html>