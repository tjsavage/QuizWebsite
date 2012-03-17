package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChallengeFactory {
	
	private static ChallengeFactory sharedInstance;
	public static ChallengeFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new ChallengeFactory();
		}
		return sharedInstance;
	}
	
	public Challenge getChallenge(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM challenges WHERE id = " + id);
		try {
			if (rs.next()) {
				Challenge challenge = new Challenge();
				challenge.setID(rs.getInt("id"));
				challenge.setFromID(rs.getInt("friendFromID"));
				challenge.setToID(rs.getInt("friendToID"));
				challenge.setQuizID(rs.getInt("quizID"));
				challenge.setCompleted(rs.getInt("completed"));
				
				QuizResultFactory qrf = QuizResultFactory.sharedInstance();
				challenge.setBestScore(qrf.retrieveUsersBestScore(challenge.getQuizID(), challenge.getFromID()));
				return challenge;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendChallenge(Challenge challenge) {
		DBConnection connection = DBConnection.sharedInstance();
		try {
				connection.insert ("INSERT INTO challenges (friendFromID, friendToID, quizID, completed) " +
						"values(" + challenge.getFromID() + " , " + challenge.getToID() + " , '" + challenge.getQuizID() + "' , 0 )");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Challenge> getSent(int id) {
		return getChallenges(id, "SELECT * FROM challenges WHERE friendFromID = ");
	}
	
	public ArrayList<Challenge> getInbox(int id) {
		return getChallenges(id, "SELECT * FROM challenges WHERE friendToID = ");
	}
	
	public ArrayList<Challenge> getChallenges(int id, String query) {
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(query + id);
		try {
			while (rs.next()) {
				Challenge challenge = new Challenge();
				challenge.setID(rs.getInt("id"));
				challenge.setFromID(rs.getInt("friendFromID"));
				challenge.setToID(rs.getInt("friendToID"));
				challenge.setQuizID(rs.getInt("quizID"));
				challenge.setCompleted(rs.getInt("completed"));
				
				QuizResultFactory qrf = QuizResultFactory.sharedInstance();
				challenge.setBestScore(qrf.retrieveUsersBestScore(challenge.getQuizID(), challenge.getFromID()));
				challenges.add(challenge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return challenges;
	}
	
}
