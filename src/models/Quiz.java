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
	
	public Quiz(String name, 
			String description, 
			int creatorID, 
			boolean ordered, 
			ArrayList<Question> questions,
			boolean multipage) {
		this.name = name;
		this.description = description;
		this.creatorID = creatorID;
		this.ordered = ordered;
		this.questions = questions;
		this.multipage = multipage;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public boolean isMultipage() {
		return multipage;
	}

	public void setMultipage(boolean multipage) {
		this.multipage = multipage;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
