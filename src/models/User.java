package models;

public class User {
	
	private String username;
	private String password;
	private boolean isLoggedIn;
	
	public User(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
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
	
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
}