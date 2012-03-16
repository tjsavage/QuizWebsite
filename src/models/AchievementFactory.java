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
