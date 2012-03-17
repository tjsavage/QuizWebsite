<div class="span4">
	<p> friends: </p>
	<%
		for (int i = 0; i < friends.size(); i++) {
			out.println("<li> <a href = \"OtherUserServlet?id=" + friends.get(i).getID() + "\">" + friends.get(i).getUsername() + "</a> </li>");
		}
	%>
	<p> pending request: </p>
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