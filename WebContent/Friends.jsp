<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.ImageFactory" %>

<%
ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
ImageFactory imgf = ImageFactory.sharedInstance();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friends</title>
<jsp:include page="HeaderInclude.jsp" />

</head>
<body class ="friend">
	<jsp:include page="templates/nav.jsp" />

<div class="container">
	<div class="wrapper">
<div class="span4">
	<p> friends: </p>
	<ul class="menu">
	<%
		for (int i = 0; i < friends.size(); i++) {
			out.println("<li> <a href = \"OtherUserServlet?id=" 
					+ friends.get(i).getID() + "\">" + friends.get(i).getUsername() 
					+ "</a><br><img src='" + imgf.getProfileImage(friends.get(i).getID()).getUrl() + "' width = '40'/> member since </li>");
		}
	%>
	</ul>
	<ul class="menu">
	<p> pending request: </p>
	<%
		ArrayList<User> pendingFriends = (ArrayList<User>) request.getAttribute("pendingFriends");
		for (int i = 0; i < pendingFriends.size(); i++) {
			out.println("<li> <a href = \"OtherUserServlet?id=" 
					+ pendingFriends.get(i).getID() + "\">" + pendingFriends.get(i).getUsername() 
					+ "</a><br><img src='" + imgf.getProfileImage(pendingFriends.get(i).getID()).getUrl() + "' width = '40'/> member since </li>");		}
	%>
	</ul>
	<ul>
	<p> friend requests: </p>
	<%
		ArrayList<User> friendRequests = (ArrayList<User>) request.getAttribute("friendRequests");
		for (int i = 0; i < friendRequests.size(); i++) {
			out.print("<li>");
			out.print("<a href = \"OtherUserServlet?id=" + friendRequests.get(i).getID() + "\">" + friendRequests.get(i).getUsername() + "</a>");
			out.print("<form method = \"post\" action = \"AddFriendServlet?id=" + friendRequests.get(i).getID() + "\" >");
			out.print("<input type = \"submit\" value= \"Add " +  friendRequests.get(i).getUsername()  + "\">");
			out.print("</form>");
			out.println("</li>");
		}
	%>
	</ul>
	
	</div>
</div>
</div>
</body>
</html>