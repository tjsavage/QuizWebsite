package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizFactory {
	private static QuizFactory sharedInstance;
	public static QuizFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new QuizFactory();
		}
		return sharedInstance;
	}
	
	public ArrayList<Quiz> retrieveAllQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet result = connection.performQuery("SELECT * FROM quizzes");
		try {
			while(result.next()) {
				Quiz currQuiz = getQuizFromResult(result);
				quizzes.add(currQuiz);
			}
			return quizzes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Quiz retrieveQuiz(int quizID) {
		String name, description;
		int creatorID;
		boolean ordered, multipage;
		Date dateCreated;
		ArrayList<Question> questions = new ArrayList<Question>();
		
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet result = connection.performQuery("SELECT * FROM quizzes WHERE id=" + quizID);
		
		try {
			if (result.next()) {;
				return getQuizFromResult(result);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Quiz getQuizFromResult(ResultSet result) throws SQLException {
		int quizID = result.getInt("id");
		String name = result.getString("name");
		String description = result.getString("description");
		int creatorID = result.getInt("creator");
		boolean ordered = result.getBoolean("ordered");
		boolean multipage = result.getBoolean("multi_page");
		Date dateCreated = result.getDate("date_created");
		ArrayList<Question> questions = retrieveQuestions(quizID);
		
		Quiz quiz = new Quiz(quizID, name, description, creatorID, ordered, questions, multipage, dateCreated);
		return quiz;
	}
	
	private ArrayList<Question> retrieveQuestions(int quizID) {
		ArrayList<Question> questions = new ArrayList<Question>();
		
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM questions WHERE quizID=" + quizID);
		QuestionFactory qf = QuestionFactory.sharedInstance();
		
		try {
			while(rs.next()) {
				int questionType = rs.getInt("question_type");
				int specificID = rs.getInt("specific_questionID");
				int order_index = rs.getInt("order_index");
				int questionID = rs.getInt("id");
				
				Question question = qf.retrieveQuestion(questionID, Question.QuestionType(questionType), specificID);
				question.setOrderIndex(order_index);
				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questions;
	}
	
	public void insertQuiz(Quiz quiz) {
		DBConnection connection = DBConnection.sharedInstance();
		String queryString = "INSERT INTO quizzes (name, description, creator, ordered, multi_page) " +
				"VALUES ('" + quiz.getSafeName() + "', '" + quiz.getSafeDescription() + "', "
				 + quiz.getCreatorID() + ", " + (quiz.isOrdered() ? 1 : 0) + ", " + (quiz.isMultipage() ? 1 : 0) + ")";
		System.out.println(queryString);
		
		int quizID = connection.insert(queryString);
		quiz.setId(quizID);
		ArrayList<Question> questions = quiz.getQuestions();
		QuestionFactory factory = QuestionFactory.sharedInstance();
		for(int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			q.setQuizID(quizID);
			q.setOrderIndex(i);
			factory.insertQuestion(q);
		}
	}
}
