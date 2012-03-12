package models;

public class Challenge {
	
//	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
//	PRIMARY KEY(id),
//	friendFromID  INT UNSIGNED NOT NULL REFERENCES users(id),
//	friendToID INT UNSIGNED NOT NULL REFERENCES users(id),
//	quizID INT UNSIGNED,
//	date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	
	private int id;
	private int friendFromID;
	private int friendToID;
	private int quizID;
	private boolean completed;
	
	public Challenge() {
		//empty constructor
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setFromID(int friendFromID) {
		this.friendFromID = friendFromID;
	}
	
	public int getFromID() {
		return friendFromID;
	}

	public void setToID(int friendToID) {
		this.friendToID = friendToID;
	}
	
	public int getToID() {
		return friendToID;
	}
	
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	
	public int getQuizID() {
		return quizID;
	}
	
	public void setCompleted(int completed) {
		this.completed = (completed == 1);		//is this right??
	}
	
	public boolean getCompleted() {
		return completed;
	}
	
}
