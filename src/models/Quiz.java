package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;

public class Quiz {
	private int id;
	

	private String name;
	private String description;
	private int creatorID;
	private boolean ordered;
	private ArrayList<Question> questions;
	private boolean multipage;
	private boolean immediateCorrection;
	private Date dateCreated;
	
	public Quiz(int id, 
			String name, 
			String description, 
			int creatorID, 
			boolean ordered, 
			ArrayList<Question> questions,
			boolean multipage,
			boolean immediateCorrection,
			Date dateCreated) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creatorID = creatorID;
		this.ordered = ordered;
		this.questions = questions;
		this.multipage = multipage;
		this.immediateCorrection = immediateCorrection;
		this.dateCreated = dateCreated;
	}
	
	public Quiz(String name, 
			String description, 
			int creatorID, 
			boolean ordered, 
			ArrayList<Question> questions,
			boolean multipage,
			boolean immediateCorrection) {
		this.name = name;
		this.description = description;
		this.creatorID = creatorID;
		this.ordered = ordered;
		this.questions = questions;
		this.multipage = multipage;
		this.immediateCorrection = immediateCorrection;
	}
	
	public User getCreator() {
		UserFactory factory = UserFactory.sharedInstance();
		return factory.getUserFromID(creatorID);
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

	public String getSafeName() {
		return this.name.replaceAll("'", "\\'");
	}
	
	public String getSafeDescription() {
		return this.description.replaceAll("'", "\\'");
	}

	public Question getQuestion(int questionIndex) {
		return questions.get(questionIndex);
	}
	
	public boolean isImmediateCorrection() {
		return immediateCorrection;
	}

	public void setImmediateCorrection(boolean immediateCorrection) {
		this.immediateCorrection = immediateCorrection;
	}

	public void shuffleQuestion() {
		if (!this.isOrdered()) {
			Collections.shuffle(questions);
		}	
	}
}
