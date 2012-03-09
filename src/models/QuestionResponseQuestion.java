package models;

import java.util.ArrayList;

public class QuestionResponseQuestion extends Question {
	private ArrayList<String> correctAnswers;
	
	public QuestionResponseQuestion(String question, ArrayList<String> correctAnswers) {
		this.question = question;
		this.correctAnswers = correctAnswers;
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
