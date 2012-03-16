package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
				password = GenerateHash(password);
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
	
	public User Register(String username, String password) {
		DBConnection connection = DBConnection.sharedInstance();
		ResultSet rs = connection.performQuery(" SELECT * FROM users WHERE username = \"" + username + "\"");
		try {
			if (!rs.next()) {
				password = GenerateHash(password);
				long userID = connection.insert("insert into users (username, password, isAdmin) values( \"" + username + "\" , \"" + password + "\", 1)");
				UserFactory uf = UserFactory.sharedInstance();
				return uf.getUserFromID((int)userID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String GenerateHash(String password){
        MessageDigest md = null;
        try {
                md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }
        byte[] bytes = md.digest(password.getBytes());
        String result = hexToString(bytes);
        return result;
	}
	
	public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
                int val = bytes[i];
                val = val & 0xff;  // remove higher bits, sign
                if (val<16) buff.append('0'); // leading 0
                buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
	}
	
}
