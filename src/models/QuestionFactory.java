package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionFactory {
	public static QuestionFactory sharedInstance;
	public static QuestionFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new QuestionFactory();
		}
		
		return sharedInstance;
	}
	public Question retrieveQuestion(int questionID, Question.QuestionType questionType, int specificID) {
		switch (questionType) {
		case QUESTION_RESPONSE:
			return retrieveQuestionResponseQuestion(questionID, specificID);
		case FILL_IN:
			return retrieveFillInQuestion(questionID, specificID);
		case MULTIPLE_CHOICE:
			return retrieveMultipleChoiceQuestion(questionID, specificID);
		case PICTURE_RESPONSE:
			return retrievePictureResponseQuestion(questionID, specificID);
		default:
			return null;
		}
	}
	
	private Question retrievePictureResponseQuestion(int questionID,
			int specificID) {
		// TODO Auto-generated method stub
		return null;
	}
	private Question retrieveMultipleChoiceQuestion(int questionID,
			int specificID) {
		// TODO Auto-generated method stub
		return null;
	}
	private Question retrieveFillInQuestion(int questionID, int specificID) {
		// TODO Auto-generated method stub
		return null;
	}
	private QuestionResponseQuestion retrieveQuestionResponseQuestion(int questionID, int specificID) {
		DBConnection connection = new DBConnection();
		
		ResultSet rs = connection.performQuery("SELECT * FROM question_response_questions WHERE id=" + specificID);
		QuestionFactory qf = QuestionFactory.sharedInstance();
		String question_text = "";
		
		try {
			rs.next();
			question_text = rs.getString("question_text");	
 		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rs = connection.performQuery("SELECT * FROM answers WHERE questionID=" + questionID);
		ArrayList<String> answers = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				answers.add(rs.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		QuestionResponseQuestion question = new QuestionResponseQuestion(questionID, question_text, answers);
		return question;	
	}

}
