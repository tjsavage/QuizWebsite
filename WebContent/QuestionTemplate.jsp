<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Quiz" %>
<%@ page import="models.Question" %>
<%@ page import="java.util.ArrayList" %>
	<input type="hidden" name="<%= index %>_id" value="<%= question.getId() %>" />
    <%
    switch (question.getTypeInt()) {
    case 0:
    	%>
    	<%@ include file="templates/question_views/question_response_question.jsp" %>
    	<%
    	break;
    case 1:
    	%>
    	<%@ include file="templates/question_views/fill_in_question.jsp" %>
    	<%
    	break;
    case 2:
    	%>
    	<%@ include file="templates/question_views/multiple_choice_question.jsp" %>
    	<%
    	break;
    case 3:
    	%>
    	<%@ include file="templates/question_views/picture_response_question.jsp" %>
    	<%
    	break;
    		
    }
    %>