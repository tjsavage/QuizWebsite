package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFactory {
	private static UserFactory sharedInstance;
	public static UserFactory sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new UserFactory();
		}
		return sharedInstance;
	}
	
	public UserFactory() {
		
	}
	
	public ArrayList<User> getListUsers(int id) {
		return getUsers(id, " SELECT * FROM users WHERE NOT id = ");
	}
	
	public ArrayList<User> getPendingFriendRequests(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs = connection.performQuery(" SELECT * FROM friend_requests WHERE friendFromID = " + id);
		try {
			while (rs.next()) {
				User user = getUserFromID(rs.getInt("friendToID"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<User> getFriendRequests(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs = connection.performQuery(" SELECT * FROM friend_requests WHERE friendToID = " + id);
		try {
			while (rs.next()) {
				User user = getUserFromID(rs.getInt("friendFromID"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<User> getFriends(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs = connection.performQuery(" SELECT * FROM friends_join WHERE friend1ID = " + id);
		try {
			while (rs.next()) {
				User user = getUserFromID(rs.getInt("friend2ID"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	private ArrayList<User> getUsers(int id, String query) {
		ArrayList<User> users = new ArrayList<User>();
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(query + id);//friends list
		try {
			while (rs.next()) {
				User user = new User(false);
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User getUserFromID(int id) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE id = " + id);
		User user = new User(false);;
		try {
			if(rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setID(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
