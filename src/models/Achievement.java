package models;

public class Achievement {

	private String description;
	private String title;
	private int id;
	
	public Achievement(){
		// empty constructor
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setID (int id) {
		this.id = id;
	}
	
	public int getID () {
		return id;
	}
	
}
