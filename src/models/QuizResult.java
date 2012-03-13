package models;

import java.sql.Date;

public class QuizResult {
	int quizID;
	int userID;
	int score;
	int completionTime;
	Date dateTaken;
	
	public QuizResult(int quizID, int userID, int score, int completionTime, Date dateTaken) {
		this.quizID = quizID;
		this.userID = userID;
		this.score = score;
		this.completionTime = completionTime;
		this.dateTaken = dateTaken;
	}
	
	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}
}
