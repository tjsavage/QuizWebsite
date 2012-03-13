package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizResultFactory {
	private static QuizResultFactory sharedInstance;
	public static QuizResultFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new QuizResultFactory();
		}
		return sharedInstance;
	}
	
	public QuizResult retrieveQuizResult(int resultID) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM quiz_results WHERE id=" + resultID);
		
		try {
			if(rs.next()) {
				int quizID = rs.getInt("quizID");
				int userID = rs.getInt("userID");
				int score = rs.getInt("score");
				Date taken = rs.getDate("date_taken");
				int completionTime = rs.getInt("completion_time");
				
				return new QuizResult(quizID, userID, score, completionTime, taken);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<QuizResult> retrieveQuizResultsForQuiz(int quizID) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM quiz_results WHERE quizID=" + quizID);
		ArrayList<QuizResult> results;
		try {
			results = new ArrayList<QuizResult>();
			while(rs.next()) {
				int userID = rs.getInt("userID");
				int score = rs.getInt("score");
				Date taken = rs.getDate("date_taken");
				int completionTime = rs.getInt("completion_time");
				
				results.add(new QuizResult(quizID, userID, score, completionTime, taken));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<QuizResult> retrieveQuizResultsForUser(int userID) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM quiz_results WHERE userID=" + userID);
		ArrayList<QuizResult> results;
		try {
			results = new ArrayList<QuizResult>();
			while(rs.next()) {
				int quizID = rs.getInt("quizID");
				int score = rs.getInt("score");
				Date taken = rs.getDate("date_taken");
				int completionTime = rs.getInt("completion_time");
				
				results.add(new QuizResult(quizID, userID, score, completionTime, taken));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertQuizResult(QuizResult result) {
		DBConnection connection = DBConnection.sharedInstance();
		String query = "INSERT INTO quiz_results (quizID, userID, score, completion_time) VALUES (" + result.getQuizID() + ", " + result.getUserID() + ", " + result.getScore() + ", " + result.getCompletionTime() + ")";
		int id = (int)connection.insert(query);
		QuizResult updatedResult = retrieveQuizResult(id);
		
		result.setDateTaken(updatedResult.getDateTaken());
	}
}
