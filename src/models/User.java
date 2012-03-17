package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User {
	
	private String username;
	private String password;
	private boolean isLoggedIn;
	private boolean isAdmin;
	private int id;
	
	public User(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) { //not setting this in login servlet
		this.isLoggedIn = isLoggedIn;
	}
	
	public int getID() {
		return id;
	}
	
	public void addFriend(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		connection.insert("INSERT INTO friends_join (friend1ID, friend2ID) values( " + this.id + " , " + id + ")");
		connection.insert("INSERT INTO friends_join (friend1ID, friend2ID) values( " + id + " , " + this.id + ")");
		connection.insert("DELETE FROM friend_requests WHERE friendFromID = " + this.id +  " AND friendToID = " + id);
		connection.insert("DELETE FROM friend_requests WHERE friendFromID = " + id + " AND friendToID = " + this.id);
	}
	
	public void removeFriend(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		connection.insert("DELETE FROM friends_join WHERE friend1ID = " + this.id +  "AND friend2ID = " + id);
		connection.insert("DELETE FROM friends_join WHERE friend1ID = " + id + " AND friend2ID = " + this.id);
	}
	
	public void sendFriendRequest(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		connection.insert("INSERT INTO friend_requests (friendFromID, friendToID) values( " + this.id + " , " + id + ")");
	}
	
	public boolean isFriend(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM friends_join WHERE friend1ID = " + this.id + " AND friend2ID = " + id);
		try {
			return (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;//on failure, should we instead return true??
	}
	
	public boolean isWaiting(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM friend_requests WHERE friendFromID = " + this.id + " AND friendToID = " + id);
		try {
			return (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;//on failure, should we instead return true??
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public boolean getAdmin() {
		return isAdmin;
	}
	
}
