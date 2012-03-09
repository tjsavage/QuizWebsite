package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Quiz {
	private int id;
	private String name;
	private String description;
	private int creatorID;
	private boolean ordered;
	private ArrayList<Question> questions;
	private boolean multipage;
	private Date dateCreated;
	
	public Quiz(int id, 
			String name, 
			String description, 
			int creatorID, 
			boolean ordered, 
			ArrayList<Question> questions,
			boolean multipage,
			Date dateCreated) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creatorID = creatorID;
		this.ordered = ordered;
		this.questions = questions;
		this.multipage = multipage;
		this.dateCreated = dateCreated;
	}
	
}
