package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {

	public UserAuthentication() {
		//empty constructor
	}
	
	public User Authenticate(String username, String password) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE username = \"" + username + "\"");
		try {
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					UserFactory factory = UserFactory.sharedInstance();
					User user = factory.getUserFromID(rs.getInt("id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean Register(String username, String password) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE username = \"" + username + "\"");
		try {
			if (!rs.next()) {
				connection.insert("insert into users (username, password, isAdmin) values( \"" + username + "\" , \"" + password + "\", 0)");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
