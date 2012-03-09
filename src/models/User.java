package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int id;
	
	public User(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
		setID();
	}
	
	private void setID() {
		int id = 0;
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE username = \"" + username + "\"");
		try {
			rs.next();
			id = rs.getInt("id");
			System.out.print(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
}