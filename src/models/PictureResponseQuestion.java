package models;

import java.util.ArrayList;

public class PictureResponseQuestion extends Question {
	public PictureResponseQuestion(int id, String imageURL, ArrayList<String> correctAnswers) {
		this.id = id;
		this.question = imageURL;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.PICTURE_RESPONSE;
	}
	
	public PictureResponseQuestion(String imageURL, ArrayList<String> correctAnswers) {
		this.question = imageURL;
		this.correctAnswers = correctAnswers;
		this.type = Question.QuestionType.PICTURE_RESPONSE;
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
