<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.UserFactory" %>
<%@ page import="models.Message" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	UserFactory uf = new UserFactory();
%>
<title>Insert title here</title>
</head>
<body>
	<h1> <% out.print(user.getUsername() + "'s page"); %> </h1>
	
	<p> inbox: </p>
	<%
		ArrayList<Message> inbox = (ArrayList<Message>) request.getAttribute("inbox");
		for (int i = 0; i < inbox.size(); i++) {
			out.print("<li>");
			out.print("<a href = \"OtherUserServlet?id=" + inbox.get(i).getFromID() + "\">" + uf.getUserFromID(inbox.get(i).getFromID()).getUsername() + "</a>");
			out.print("<a href='ReadMessageServlet?id=" + inbox.get(i).getID() + "'> Read Message</a>");
			out.println("</li>");
		}
	%>
	<p> sent: </p>
	<%
		ArrayList<Message> sent = (ArrayList<Message>) request.getAttribute("sent");
		for (int i = 0; i < sent.size(); i++) {
			out.print("<li>");
			out.print("<a href = \"OtherUserServlet?id=" + sent.get(i).getToID() + "\">" + uf.getUserFromID(sent.get(i).getToID()).getUsername() + "</a>");
			out.print("<a href='ReadMessageServlet?id=" + sent.get(i).getID() + "'> Read Message</a>");
			out.println("</li>");
		}
	%>
	<p> friends: </p>
	<%
		ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
		for (int i = 0; i < friends.size(); i++) {
			out.println("<li> <a href = \"OtherUserServlet?id=" + friends.get(i).getID() + "\">" + friends.get(i).getUsername() + "</a> </li>");
		}
	%>
	<p> pending requests: </p>
	<%
		ArrayList<User> pendingFriends = (ArrayList<User>) request.getAttribute("pendingFriends");
		for (int i = 0; i < pendingFriends.size(); i++) {
			out.println("<li> <a href = \"OtherUserServlet?id=" + pendingFriends.get(i).getID() + "\">" + pendingFriends.get(i).getUsername() + "</a> </li>");
		}
	%>
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
	<%	 
		out.print("<a href = \"FriendSearchServlet\"> find friends </a>");
	%>
</body>
</html>