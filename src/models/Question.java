package models;

import java.util.ArrayList;

import models.Question.QuestionType;

public class Question {
	enum QuestionType {
		QUESTION_RESPONSE,
		FILL_IN,
		MULTIPLE_CHOICE,
		PICTURE_RESPONSE,
	}
	
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

	public static QuestionType QuestionType(int questionType) throws Exception {
		switch(questionType) {
		case 0:
			return QuestionType.QUESTION_RESPONSE;
		case 1:
			return QuestionType.FILL_IN;
		case 2:
			return QuestionType.MULTIPLE_CHOICE;
		case 3:
			return QuestionType.PICTURE_RESPONSE;
		default:
			throw new Exception("Undefined question type: " + questionType);
		}
	}
}
