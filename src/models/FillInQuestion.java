package models;

import java.util.ArrayList;

public class FillInQuestion extends Question {
	public FillInQuestion(int id, String prompt, ArrayList<String> correctAnswers) {
		this.id = id;
		this.question = prompt;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.FILL_IN;
	}
	
	public FillInQuestion(String question, ArrayList<String> correctAnswers) {
		this.question = question;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.FILL_IN;
	}
	
	public boolean isCorrectAnswer(String answer) {
		return correctAnswers.contains(answer);
	}
	
	public String getAnswerString() {
		String answer = correctAnswers.get(0);
		for(String str : correctAnswers) {
			answer += ", " + str;
		}
		return answer;
	}
}
