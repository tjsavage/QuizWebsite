<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.User" %>
<%@ page import="models.UserFactory" %>
<%@ page import="models.Message" %>
<%@ page import="models.Image" %>
<%@ page import="models.Anouncement" %>
<%@ page import="models.AdminControl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Achievement" %>
<%@ page import="models.AchievementFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="HeaderInclude.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getSession().getAttribute("user");
	Image profileImage = (Image) request.getAttribute("profileImage");
	UserFactory uf = new UserFactory();
	AdminControl ac = new AdminControl();
	ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
%>
<title><%= user.getUsername() %></title>
</head>
<body>
	<jsp:include page="templates/nav.jsp" />
	<div class="container">
	<div class="wrapper">
		<h1> <% out.print(user.getUsername() + "'s Admin page"); %> </h1>
		
		<div class="span4">
		<a href = "ViewImagesServlet?userID=<%=user.getID()%>"> <img class="imagedropshadow" src="<%= profileImage.getUrl() %>" width = "205"/></a>
		<br>
		<a href = "AddImageServlet" class = "btn" > add a picture</a>
		<a href = "FriendSearchServlet" class = "btn" >   find friends  </a>
		<a href = "ChallengeServlet" class = "btn" >   view challenges  </a>
		<br>
		<a href = "SendAnouncementServlet" class = "btn" >   make an announcement  </a>
		<a href = "FriendsServlet" >   <h2>Friends (<%=friends.size() %>)</h2>  </a>
		<h3> Number of users: <%= ac.getNumUsers() %> </h3>
		<h3> Number of quizzes taken: <%= ac.getNumTaken() %> </h3>
		<h2> recent announcements: </h2>
		<ul class="menu">
		<%
			ArrayList<Anouncement> anouncements = (ArrayList<Anouncement>) request.getAttribute("anouncements");
			for (int i = 0; i < anouncements.size(); i++) {
				out.print("<li>");
				out.print(anouncements.get(i).getAnouncement());
				out.println("</li>");
			}
		%>
		</ul>
		<h2> Your achievements: </h2>
		<ul class="menu">
		<%
			AchievementFactory af = new AchievementFactory();
			ArrayList<Achievement> achievements = (ArrayList<Achievement>) af.checkForAchievements(user.getID());
			for (int i = 0; i < achievements.size(); i++) {
				out.print("<li class=\"menu\">");
				out.print(achievements.get(i).getTitle()+ "<br>");
				out.print(achievements.get(i).getDescription());
				out.println("</li>");
			}
		%>
		</ul>
		<h2> inbox: </h2>
		<ul class= "mail">
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
		<h2> sent: </h2>
		<ul class= "mail">
		<%
			ArrayList<Message> sent = (ArrayList<Message>) request.getAttribute("sent");
			for (int i = 0; i < sent.size(); i++) {
				out.print("<li>");
				out.print("<a href = \"OtherUserServlet?id=" + sent.get(i).getToID() + "\">" + uf.getUserFromID(sent.get(i).getToID()).getUsername() + "</a>");
				out.print("<a href='ReadMessageServlet?id=" + sent.get(i).getID() + "'> Read Message</a>");
				out.println("</li>");
			}
		%>
		</ul>
		</div>

		<jsp:include page="templates/recent_activity.jsp" />
	</div>
	</div>
</body>
</html>