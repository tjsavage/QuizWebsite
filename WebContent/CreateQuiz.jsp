<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Quiz</title>

<script type="text/javascript" src="/QuizWebsite/js/jquery.js"></script>
<script type="text/javascript" src="/QuizWebsite/js/jquery-ui.js"></script>
<script type="text/javascript" src="/QuizWebsite/js/quizCreation.js"></script>
</head>
<body>

<form method="POST">
	<fieldset>
		<legend>Quiz Info</legend>
		<label for="name">Name:</label>
		<input type="text" name="name" />
		<label for="description">Description:</label>
		<textarea name="description"></textarea>
		<label for="ordered">Ordered:</label>
		<input type="checkbox" name="ordered" />
		<label for="multi_page">Each question on separate page:</label>
		<input type="checkbox" name="multi_page" />
	</fieldset>
	<fieldset id="questions">
		
	</fieldset>
	
	<input type="hidden" value="0" name="num_questions" id="num_questions" />
	<input type="submit" value="Submit" />
</form>

<div>
	<select id="new_question_type">
		<option value="0">Question-Response</option>
		<option value="1">Fill-in-the-Blank</option>
		<option value="2">Multiple Choice</option>
		<option value="3">Picture-Response</option>
	</select>
	<a href="javascript:addQuestion()">Add Question</a>
</div>

</body>
</html>