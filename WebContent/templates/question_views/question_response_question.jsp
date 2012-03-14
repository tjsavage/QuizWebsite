<fieldset id="<%= index %>_block">
	<label><%= question.getQuestion() %></label>
	<input type="hidden" name="<%= index %>_type" value="0" />
	<input type="text" name="<%= index %>_answer" id="<%= index %>_answer" />
</fieldset>