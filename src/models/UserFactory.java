package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFactory {
	
	public UserFactory() {
		
	}
	
	public ArrayList<User> getListUsers(int id) {
		ArrayList<User> users = new ArrayList<User>();
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE NOT id = " + id);
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
	
}
