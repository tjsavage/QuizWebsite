var questionIndex = 0;

function addQuestion() {
	var question_type = $("#new_question_type").val();
	switch (parseInt(question_type)) {
	case 0:
		$.get("/QuizWebsite/templates/question_types/question_response_question.jsp?qIndex=" + questionIndex,
				function(data) {
			$("#questions").append(data);
		});
		break;
	case 1:
		$.get("/QuizWebsite/templates/question_types/fill_in_question.jsp?qIndex=" + questionIndex,
				function(data) {
			$("#questions").append(data);
		});
		break;
	case 2:
		$.get("/QuizWebsite/templates/question_types/multiple_choice_question.jsp?qIndex=" + questionIndex,
				function(data) {
			$("#questions").append(data);
		});
		break;
	case 3:
		$.get("/QuizWebsite/templates/question_types/picture_response_question.jsp?qIndex=" + questionIndex,
				function(data) {
			$("#questions").append(data);
		});
		break;
	default:
		alert("Invalid question type");
	}
	questionIndex++;
	$("#num_questions").val(questionIndex);
}

function deleteBlock(index) {
	$("#" + index + "_block").remove();
	for(var i = index + 1; i < questionIndex; i++) {
		var block = $("#" + i + "_block");
		var new_html = replaceAll(block.html(), "" + i + "_", "" + (i - 1) + "_");
		new_html = replaceAll(new_html, "\\(" + i + "\\)", "(" + (i-1) + ")");
	
		block.html(new_html);
		block.attr("id", "" + (i-1) + "_block");
	}
	questionIndex--;
	$("#num_questions").val(questionIndex);
}

function addAnswerField(index) {
	var answer_block = $("#" + index + "_answers");
	var num_answers = parseInt($("#" + index + "_num_answers").val());
	answer_block.append("<input type='text' name='" + index + "_answer_" + num_answers + "' />");
	num_answers++;
	$("#" + index + "_num_answers").val(num_answers);
}

function addChoice(index) {
	var choices_block = $("#" + index + "_choices");
	var num_choices = parseInt($("#" + index + "_num_choices").val());
	choices_block.append("<input type='text' name='" + index + "_choice_" + num_choices + "' />");
	num_choices++;
	$("#" + index + "_num_choices").val(num_choices);
}

function replaceAll(input, replace, replacement) {
	  return input.replace(new RegExp(replace, 'g'),replacement);
	}