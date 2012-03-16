package models;

import java.sql.Timestamp;

public class Image {
	
	private String url;
	private int userID;
	private int id;
	Timestamp timestamp;
	private boolean isProfile;
	
	public Image() {
		//empty constructor
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setIsProfile(boolean isProfile) {
		this.isProfile = isProfile;
	}
	
	public boolean isProfile() {
		return isProfile;
	}

}
