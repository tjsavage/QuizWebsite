<%@ page import="models.MultipleChoiceQuestion" %>

<fieldset id="<%= index %>_block">
	<label><%= question.getQuestion() %></label>
	<% MultipleChoiceQuestion mcQ = (MultipleChoiceQuestion)question; %>
	<% for(int i = 0; i < mcQ.getChoices().size(); i++) {
		out.println("<input type='radio' name='" + index + "_answer' value='" + mcQ.getChoices().get(i) + "'>" + mcQ.getChoices().get(i) + "</option>");
	}
	%>
	</select>
</fieldset>