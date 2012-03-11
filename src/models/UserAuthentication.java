package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {

	public UserAuthentication() {
		//empty constructor
	}
	
	public boolean Authenticate(String username, String password) {
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE username = \"" + username + "\"");
		try {
			if (rs.next()) return (rs.getString("password").equals(password));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean Register(String username, String password) {
		DBConnection connection = new DBConnection();
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
