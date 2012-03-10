package models;

public class Message {

	private int friendFromID;
	private int friendToID;
	private String message;
	private boolean read;
	
	public Message( ) {
		
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}
	
	public boolean getRead() {
		return read;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setFromID(int friendFromID) {
		this.friendFromID = friendFromID;
	}
	
	public int getFromID() {
		return friendFromID;
	}
	
	public void setToID(int friendToID) {
		this.friendToID = friendToID;
	}
	
	public int getToID() {
		return friendToID;
	}
	
}
