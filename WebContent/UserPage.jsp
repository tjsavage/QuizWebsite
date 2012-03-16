<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.Anouncement" %>
<%@ page import="models.UserFactory" %>
<%@ page import="models.Message" %>
<%@ page import="models.Image" %>
<%@ page import="models.QuizResult" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="HeaderInclude.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	Image profileImage = (Image) request.getAttribute("profileImage");
	UserFactory uf = new UserFactory();
%>
<title><%= user.getUsername() %></title>
</head>
<body>
	<jsp:include page="templates/nav.jsp" />
	<div class="container">
	<div class="wrapper">
		<h1> <% out.print(user.getUsername() + "'s page"); %> </h1>
		
		<div class="span4">
		<img src="<%= profileImage.getUrl() %>" width = "175"/>
		<br>
		<form method = "get" action = "AddImageServlet" >
			<p> 
				<input type = "submit" value= "Add a picture to your profile">
			</p>
		</form>
		<form method = "get" action = "ViewImagesServlet" >
			<p> 
				<input type = "hidden" name = "userID" value = "<%= user.getID() %>" >
				<input type = "submit" value = "Veiw your pictures">
			</p>
		</form>
		<p> recent announcements: </p>
		<%
			ArrayList<Anouncement> anouncements = (ArrayList<Anouncement>) request.getAttribute("anouncements");
			for (int i = 0; i < anouncements.size(); i++) {
				out.print("<li>");
				out.print(anouncements.get(i).getAnouncement());
				out.println("</li>");
			}
		%>
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
		</div>
		<div class="span4">
		<p> friends: </p>
		<%
			ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
			for (int i = 0; i < friends.size(); i++) {
				out.println("<li> <a href = \"OtherUserServlet?id=" + friends.get(i).getID() + "\">" + friends.get(i).getUsername() + "</a> </li>");
			}
		%>
		<p> pending requestz: </p>
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
		
		</div>
		<form method = "get" action = "ChallengeServlet" >
			<p> 
				<input type = "submit" value= "View Challenges">
			</p>
		</form>
		<%	 
			out.print("<a href = \"FriendSearchServlet\"> find friends </a>");
		%>
		<jsp:include page="templates/recent_activity.jsp" />
	</div>
	</div>
</body>
</html>