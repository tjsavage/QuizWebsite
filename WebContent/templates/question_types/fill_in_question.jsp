<fieldset id="<%= request.getParameter("qIndex") %>_block">
	<legend>Fill-in-the-Blank Question</legend>
	<label>Prompt</label>
	<input type="hidden" name="<%= request.getParameter("qIndex") %>_type" value="1" />
	
	<input type="text" name="<%= request.getParameter("qIndex") %>_question" />
	<fieldset id="<%= request.getParameter("qIndex") %>_answers">
		<legend>Correct Answers</legend>
		<input type="text" name="<%= request.getParameter("qIndex") %>_answer_0" />
	</fieldset>
	<input type="hidden" name="<%= request.getParameter("qIndex") %>_num_answers" id="<%= request.getParameter("qIndex") %>_num_answers" value="1" />
	<a href="javascript:addAnswerField(<%= request.getParameter("qIndex") %>)">Add Answer</a>
	<a href="javascript:deleteBlock(<%= request.getParameter("qIndex") %>)">Delete Question</a>
</fieldset>