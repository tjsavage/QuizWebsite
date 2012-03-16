package models;

import java.sql.Timestamp;

public class Anouncement {
	private int adminID;
	private int id;
	private String anouncement;
	boolean isAdminOnly;
	Timestamp timestamp;
	
	public Anouncement( ) {
		
	}
	
	public void setAnouncement(String anouncement) {
		this.anouncement = anouncement;
	}
	
	public String getAnouncement() {
		return anouncement;
	}
	
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	
	public int getAdminID() {
		return adminID;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setIsAdminOnly(boolean isAdminOnly) {
		this.isAdminOnly = isAdminOnly;
	}
	
	public boolean getIsAdminOnly() {
		return isAdminOnly;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
}
