package models;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {
	ArrayList<String> choices;
	
	public MultipleChoiceQuestion(int id, String prompt, ArrayList<String> choices, String answer) {
		this.id = id;
		this.question = prompt;
		this.correctAnswers = new ArrayList<String>();
		this.correctAnswers.add(answer);
		this.choices = choices;
		this.type = Question.QuestionType.MULTIPLE_CHOICE;
	}
	
	public MultipleChoiceQuestion(String question, ArrayList<String> choices, String answer) {
		this.question = question;
		this.correctAnswers = new ArrayList<String>();
		this.correctAnswers.add(answer);
		this.choices = choices;
		this.type = Question.QuestionType.MULTIPLE_CHOICE;
	}
	
	public String getAnswerString() {
		String answer = correctAnswers.get(0);
		for(int i = 1; i < correctAnswers.size(); i++) {
			answer += ", " + correctAnswers.get(i);
		}
		return answer;
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}
}
