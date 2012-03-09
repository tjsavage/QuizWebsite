package models;

import java.util.ArrayList;

public abstract class Question {
	public enum QUESTION_TYPE {
		QUESTION_RESPONSE,
		FILL_IN,
		MULTIPLE_CHOICE,
		PICTURE_RESPONSE
	};
	
	protected int id;
	protected int quizID;
	protected String question;
	protected int orderIndex;
	
	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Question() {
		
	}
	
	public boolean isCorrectAnswer(Object answer) {
		return false;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAnswerString() {
		return "";
	}
}
