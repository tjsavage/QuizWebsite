package models;

public class FriendRequest {
	
	private int FriendFromId;
	private int FriendToId;
	private String UsernameFrom;
	private String UsernameTo;
	
	public FriendRequest(String UsernameFrom, String UsernameTo) {
		this.UsernameFrom = UsernameFrom;
		this.UsernameTo = UsernameTo;
		
	}

}
