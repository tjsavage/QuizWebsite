function showQuestion(index) {
	$(".question_block").each(function(index, elem) { $(this).hide(); })
	$("#result").hide();
	$("#question_" + index).show();
}

function checkAnswer(index, questionID) {
	var answer = $("#" + index + "_answer").val();
	if (!answer) {
		answer = $("#" + index + "_answer:checked").val();
	}
	$.post('SingleQuestionServlet', "questionID=" + questionID + "&answer=" + answer, function(data, textStatus) {
		
		alert(data);
		var result = $.parseJSON(data)['question'];
		$("#result_status").html(result['correct'] ? "Correct!" : "Wrong");
		$("#result_correct_answers").html(result['answers']);
		$("#result_next_question").html("<a href='javascript:showQuestion(" + (index + 1) + ")'>Next Question</a>" );
	
		$("#result").show();
		$(".question_block").each(function(index, elem) { $(this).hide(); })
	});
}