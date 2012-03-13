package models;

import java.util.ArrayList;

public class QuestionResponseQuestion extends Question {
	public QuestionResponseQuestion(int id, String question, ArrayList<String> correctAnswers) {
		this.id = id;
		this.question = question;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.QUESTION_RESPONSE;
	}
	
	public QuestionResponseQuestion(String question, ArrayList<String> correctAnswers) {
		this.question = question;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.QUESTION_RESPONSE;
	}
	
	public String getAnswerString() {
		String answer = correctAnswers.get(0);
		for(String str : correctAnswers) {
			answer += ", " + str;
		}
		return answer;
	}
}
