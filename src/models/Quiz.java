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
	
	public Quiz(int id) {
		this.id = id;
		questions = new ArrayList<Question>();
		DBConnection connection = new DBConnection();
		ResultSet result = connection.performQuery("SELECT * FROM quizzes WHERE id=" + id);
		
		try {
			result.next();
			name = result.getString("name");
			description = result.getString("description");
			creatorID = result.getInt("creator");
			ordered = result.getBoolean("ordered");
			multipage = result.getBoolean("multi_page");
			dateCreated = result.getDate("date_created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		retrieveQuestions();
	}
	
	private void retrieveQuestions() {
		DBConnection connection = new DBConnection();
		ResultSet rs = connection.performQuery("SELECT * FROM questions WHERE quizID=" + id);
		
		try {
			while(rs.next()) {
				int questionType = rs.getInt("quiz_type");
				int specificID = rs.getInt("specific_questionID");
				int order_index = rs.getInt("order_index");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
