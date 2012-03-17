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
	ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
%>
<title><%= user.getUsername() %></title>
</head>
<body>
	<jsp:include page="templates/nav.jsp" />
	<div class="container">
	<div class="wrapper">
		<h1> <% out.print(user.getUsername() + "'s page"); %> </h1>
		<br>
		<div class="span4">
		<a href = "ViewImagesServlet?userID=<%=user.getID()%>"> <img class="imagedropshadow" src="<%= profileImage.getUrl() %>" width = "205"/></a>
		<br>
		<a href = "AddImageServlet" class = "btn" > add a picture</a>
		<a href = "FriendSearchServlet" class = "btn" >   find friends  </a>
		<br>
		<a href = "FriendSearchServlet" >   <h2>Friends (<%=friends.size() %>)</h2>  </a>
		
		<h2> recent announcements: </h2>
		<ul class="menu">
		<%
			ArrayList<Anouncement> anouncements = (ArrayList<Anouncement>) request.getAttribute("anouncements");
			for (int i = 0; i < anouncements.size(); i++) {
				out.print("<li class=\"menu\">");
				out.print(anouncements.get(i).getAnouncement());
				out.println("</li>");
			}
		%>
		</ul>
		<p> inbox: </p>
		<ul id="list1">
		<%
			ArrayList<Message> inbox = (ArrayList<Message>) request.getAttribute("inbox");
			for (int i = 0; i < inbox.size(); i++) {
				out.print("<li>");
				out.print("<a href = \"OtherUserServlet?id=" + inbox.get(i).getFromID() + "\">" + uf.getUserFromID(inbox.get(i).getFromID()).getUsername() + "</a>");
				out.print("<a href='ReadMessageServlet?id=" + inbox.get(i).getID() + "'> Read Message</a>");
				out.println("</li>");
			}
		%>
		</ul>
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
		<form method = "get" action = "ChallengeServlet" >
			<p> 
				<input type = "submit" value= "View Challenges">
			</p>
		</form>
		<jsp:include page="templates/recent_activity.jsp" />
	</div>
	</div>
</body>
</html>