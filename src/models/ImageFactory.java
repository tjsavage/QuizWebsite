package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageFactory {
	
	private String defaultUrl = "http://28.media.tumblr.com/tumblr_m101k4lsvB1rpefl4o1_250.png";
	
	public static ImageFactory sharedInstance;
	public static	ImageFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new ImageFactory();
		}
		
		return sharedInstance;
	}
	
	public void addPicture(Image image) {
		DBConnection connection = DBConnection.sharedInstance();
		int isProfile = 0;
		if (image.isProfile()) isProfile = 1;
		try {
				connection.insert ("INSERT INTO images (userID, url, isProfile) " +
						"values(" + image.getUserID() + " , \"" + image.getUrl() +"\" , " + isProfile + " )");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Image getProfileImage(int userID) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery("SELECT * FROM images WHERE userID = " + userID + " AND isProfile = 1");
		Image image = new Image();
		try {
			if (rs.next()) {
				image.setUrl(rs.getString("url"));
				image.setID(rs.getInt("id"));
				image.setUserID(userID);
				image.setTimestamp(rs.getTimestamp("date_created"));
				image.setIsProfile(true);
				return image;
			} else {
				image.setUrl(defaultUrl);
				image.setID(-1);
				image.setUserID(userID);
				image.setTimestamp(null);
				image.setIsProfile(true);
				return image;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Image> getAllImages(int userID) {
		DBConnection connection = DBConnection.sharedInstance();
		ArrayList<Image> images = new ArrayList<Image>();
		ResultSet rs = connection.performQuery("SELECT * FROM images WHERE userID = " + userID );
		try {
			while (rs.next()) {
				Image image = new Image();
				image.setUrl(rs.getString("url"));
				image.setID(rs.getInt("id"));
				image.setUserID(userID);
				image.setTimestamp(rs.getTimestamp("date_created"));
				image.setIsProfile(1 == rs.getInt("isProfile"));
				images.add(image);
			}
			if (images.size() == 0) {
				Image image = new Image();
				image.setUrl(defaultUrl);
				image.setID(-1);
				image.setUserID(userID);
				image.setTimestamp(null);
				images.add(image);
			}
			return images;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void makeProfilePicture(int imageID, int userID) {
		DBConnection connection = DBConnection.sharedInstance();
		int oldID = getProfileImage(userID).getID();
		connection.insert("UPDATE images SET isProfile = 0 WHERE id = " + oldID);
		connection.insert("UPDATE images SET isProfile = 1 WHERE id = " + imageID);
	}
	
	public void deletePicture(int imageID) {
		DBConnection connection = DBConnection.sharedInstance();
		connection.insert("DELETE FROM images WHERE id = " + imageID);
	}
	
}
