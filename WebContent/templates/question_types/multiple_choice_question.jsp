<fieldset id="<%= request.getParameter("qIndex") %>_block">
	<legend>Multiple Choice</legend>
	<label>Question</label>
	<input type="hidden" name="<%= request.getParameter("qIndex") %>_type" value="2" />
	<input type="text" name="<%= request.getParameter("qIndex") %>_question" />
	<fieldset id="<%= request.getParameter("qIndex") %>_choices">
		<legend>Choices</legend>
		<input type="text" name="<%= request.getParameter("qIndex") %>_choice_0" />
	</fieldset>
	<fieldset id="<%= request.getParameter("qIndex") %>_answers">
		<legend>Correct Answer</legend>
		<input type="text" name="<%= request.getParameter("qIndex") %>_answer_0" />
	</fieldset>
	<input type="hidden" name="<%= request.getParameter("qIndex") %>_num_answers" id="<%= request.getParameter("qIndex") %>_num_answers" value="1" />
	<input type="hidden" name="<%= request.getParameter("qIndex") %>_num_choices" id="<%= request.getParameter("qIndex") %>_num_choices" value="1" />
	<a href="javascript:addChoice(<%= request.getParameter("qIndex") %>)">Add Choice</a>
	<a href="javascript:deleteBlock(<%= request.getParameter("qIndex") %>)">Delete Question</a>
</fieldset>