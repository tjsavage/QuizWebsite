package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnouncementFactory {
	private static AnouncementFactory sharedInstance;
	public static AnouncementFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new AnouncementFactory();
		}
		return sharedInstance;
	}
	
	public void sendAnouncement(Anouncement anouncement) {
		DBConnection connection = DBConnection.sharedInstance();
		int isAdminOnly = 0;
		if (anouncement.getIsAdminOnly()) isAdminOnly = 1;
		try {
				connection.insert ("INSERT INTO anouncements (adminID, anouncement, isAdminOnly) " +
						"values(" + anouncement.getAdminID() + " , \"" + anouncement.getAnouncement() +"\" , " + isAdminOnly + " )");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Anouncement> getAdminAnouncements() {
		ArrayList<Anouncement> anouncements = new ArrayList<Anouncement>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM anouncements");
		try {
			while (rs.next()) {
				Anouncement anouncement = new Anouncement();
				anouncement.setID(rs.getInt("id"));
				anouncement.setAdminID(rs.getInt("adminID"));
				anouncement.setAnouncement(rs.getString("anouncement"));
				boolean isAdminOnly = (rs.getInt("isAdminOnly") == 1);
				anouncement.setIsAdminOnly(isAdminOnly);
				anouncement.setTimestamp(rs.getTimestamp("date_created"));
				anouncements.add(anouncement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anouncements;
	}
	
	public ArrayList<Anouncement> getUserAnouncements() {
		ArrayList<Anouncement> anouncements = new ArrayList<Anouncement>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM anouncements WHERE NOT isAdminOnly = 1");
		try {
			while (rs.next()) {
				Anouncement anouncement = new Anouncement();
				anouncement.setID(rs.getInt("id"));
				anouncement.setAdminID(rs.getInt("adminID"));
				anouncement.setAnouncement(rs.getString("anouncement"));
				boolean isAdminOnly = (rs.getInt("isAdminOnly") == 1);
				anouncement.setIsAdminOnly(isAdminOnly);
				anouncement.setTimestamp(rs.getTimestamp("date_created"));
				anouncements.add(anouncement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anouncements;
	}
	
	public ArrayList<Anouncement> getFiveMostRecentAdmin() {
		return getFiveMostRecentAnouncements("SELECT * FROM  anouncements ORDER BY date_created DESC LIMIT 5");
	}
	
	public ArrayList<Anouncement> getFiveMostRecentUser() {
		return getFiveMostRecentAnouncements("SELECT * FROM  anouncements WHERE isAdminOnly = 0 ORDER BY date_created DESC LIMIT 5");
	}
	
	private ArrayList<Anouncement> getFiveMostRecentAnouncements(String query) {
		ArrayList<Anouncement> anouncements = new ArrayList<Anouncement>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(query);
		try {
			while (rs.next()) {
				Anouncement anouncement = new Anouncement();
				anouncement.setID(rs.getInt("id"));
				anouncement.setAdminID(rs.getInt("adminID"));
				anouncement.setAnouncement(rs.getString("anouncement"));
				boolean isAdminOnly = (rs.getInt("isAdminOnly") == 1);
				anouncement.setIsAdminOnly(isAdminOnly);
				anouncement.setTimestamp(rs.getTimestamp("date_created"));
				anouncements.add(anouncement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anouncements;
	}
	
}
