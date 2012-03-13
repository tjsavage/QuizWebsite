<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="models.Question" %>
<%@ page import="java.util.ArrayList" %>

<% Quiz quiz = (Quiz)request.getAttribute("quiz"); 
	Question question = (Question)request.getAttribute("question");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= quiz.getName() %></title>
</head>
<body>
<h1><%= quiz.getName() %></h1>
<jsp:include page="QuestionTemplate.jsp" />
</body>
</html>