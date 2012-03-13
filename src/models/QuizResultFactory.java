package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class QuizResultFactory {
	private static QuizResultFactory sharedInstance;
	public static QuizResultFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new QuizResultFactory();
		}
		return sharedInstance;
	}
	
	public enum SortingMethod  {
		DATE,
		SCORE
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
	
	public ArrayList<QuizResult> retrieveSortedQuizResultsForQuiz(int quizID, SortingMethod sortBy) {
		ArrayList<QuizResult> results = retrieveQuizResultsForQuiz(quizID);
		
		switch (sortBy) {
		case DATE:
			Collections.sort(results, new DateComparator());
			break;
		case SCORE:
			Collections.sort(results, new ScoreComparator());
			break;
		}
		
		return results;
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
	
	class DateComparator implements Comparator<QuizResult> {
		public int compare(QuizResult result1, QuizResult result2) {
			return (result1.getDateTaken().getTime() < result2.getDateTaken().getTime()) ? 1 : -1;
		}
	}
	
	class ScoreComparator implements Comparator<QuizResult> {
		public int compare(QuizResult result1, QuizResult result2) {
			if (result1.getScore() == result2.getScore()) {
				return  result1.getCompletionTime() - result2.getCompletionTime();
			} else {
				return  result2.getScore() - result1.getScore();
			}
		}
	}
}
