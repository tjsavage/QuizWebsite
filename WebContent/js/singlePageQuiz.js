function showQuestion(index) {
	$(".question_block").each(function(index, elem) { $(this).hide(); })
	$("#question_" + index).show();
}