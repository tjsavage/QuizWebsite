package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchievementFactory {
	
	public AchievementFactory() {
		//empty constructor
	}
	
	public void addAchievement(Achievement ach) {
		DBConnection connection = DBConnection.sharedInstance();
		try {
				connection.insert ("INSERT INTO achievements (title, description) " +
						"values('" + ach.getTitle() + "' , '" + ach.getDescription() + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Achievement getAchievement(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM achievements WHERE id = " + id);
		try {
			if (rs.next()) {
				Achievement ach = new Achievement();
				ach.setTitle(rs.getString("text"));
				ach.setDescription(rs.getString("description"));
				ach.setID(rs.getInt("id"));
				return ach;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Achievement> getAllAchievements() {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM achievements");
		try {
			while (rs.next()) {
				Achievement ach = new Achievement();
				ach.setTitle(rs.getString("text"));
				ach.setDescription(rs.getString("description"));
				ach.setID(rs.getInt("id"));
				achievements.add(ach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievements;
	}
	
	public ArrayList<Achievement> getUserAchievements(int id) {
		ArrayList<Integer> ids = getUserIDs(id);
		ArrayList<Achievement> userAchievements = new ArrayList<Achievement>();
		for (int i = 0; i < ids.size(); i++) {
			userAchievements.add(getAchievement(id));
		}
		return userAchievements;
	}
	
	private ArrayList<Integer> getUserIDs(int id) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM achievements WHERE userID = " + id);
		try {
			while (rs.next()) {
				int achievementID = rs.getInt("userID");
				if (! ids.contains(id)) ids.add(achievementID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public ArrayList<Achievement> checkForAchievements(int userID) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM quiz_results WHERE userID = " + userID);
		ResultSet rs2 = connection.performQuery("SELECT * FROM quizzes WHERE creator = " + userID);
		ResultSet rs3 = connection.performQuery("SELECT * FROM messages WHERE friendFromID = " + userID);
		ResultSet rs4 = connection.performQuery("SELECT * FROM messages WHERE friendToID = " + userID);
		try {
			int numR = 0;
			while (rs.next()) {
				numR++;
			}
			if (numR > 10) {
				Achievement ach = new Achievement();
				ach.setTitle("I get results.");
				ach.setDescription("you've completed more than 10 quizes!");
				ach.setID(userID);
				achievements.add(ach);
			}
			int numQ = 0;
			while (rs2.next()) {
				numQ++;
			}
			if (numQ > 9) {
				Achievement ach = new Achievement();
				ach.setTitle("Master creator.");
				ach.setDescription("you've created 10 quizzes!");
				ach.setID(userID);
				achievements.add(ach);
			}
			if (numQ > 0) {
				Achievement ach = new Achievement();
				ach.setTitle("Apprentice creator.");
				ach.setDescription("you've created a quiz!");
				ach.setID(userID);
				achievements.add(ach);
			}
			if (numQ > 4) {
				Achievement ach = new Achievement();
				ach.setTitle("advanced creator.");
				ach.setDescription("you've created 5 quizzes!");
				ach.setID(userID);
				achievements.add(ach);
			}
			int numS = 0;
			while (rs3.next()) {
				numS++;
			}
			if (numS > 5) {
				Achievement ach = new Achievement();
				ach.setTitle("big talker.");
				ach.setDescription("you've sent more than 5 messages");
				ach.setID(userID);
				achievements.add(ach);
			}
			int numG = 0;
			while (rs4.next()) {
				numG++;
			}
			if (numG > 5) {
				Achievement ach = new Achievement();
				ach.setTitle("popular.");
				ach.setDescription("you've received more than 5 messages");
				ach.setID(userID);
				achievements.add(ach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievements;
	}
	
	public void awardUser(int userID, int achievementID) {
		DBConnection connection = DBConnection.sharedInstance();
		try {
				connection.insert ("INSERT INTO achievementsAwarded (userID, achievementID) " +
						"values(" + userID + " , " + achievementID + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
