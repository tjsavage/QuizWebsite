USE c_cs108_tjsavage;

CREATE TABLE IF NOT EXISTS users(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	username VARCHAR(40) NOT NULL,
	password VARCHAR(65) NOT NULL,
	isAdmin INT
);

CREATE TABLE IF NOT EXISTS friends_join(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	friend1ID  INT UNSIGNED NOT NULL REFERENCES users(id),
	friend2ID INT UNSIGNED NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS friend_requests(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	friendFromID  INT UNSIGNED NOT NULL REFERENCES users(id),
	friendToID INT UNSIGNED NOT NULL REFERENCES users(id),
	date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS messages(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	friendFromID  INT UNSIGNED NOT NULL REFERENCES users(id),
	friendToID INT UNSIGNED NOT NULL REFERENCES users(id),
	message TEXT,
	readMark BOOLEAN,
	date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS challenges(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	friendFromID  INT UNSIGNED NOT NULL REFERENCES users(id),
	friendToID INT UNSIGNED NOT NULL REFERENCES users(id),
	quizID INT UNSIGNED,
	completed BOOLEAN,
	date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS quizzes(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	name VARCHAR(40) NOT NULL,
	description TEXT,
	creator INT UNSIGNED NOT NULL REFERENCES users(id),
	ordered BOOLEAN,
	multi_page BOOLEAN,
	immediate_correction BOOLEAN DEFAULT false,
	date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS questions(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	quizID INT UNSIGNED NOT NULL REFERENCES quizzes(id),
	question_type INT NOT NULL,
	specific_questionID INT UNSIGNED NOT NULL,
	order_index INT
);

CREATE TABLE IF NOT EXISTS question_response_questions(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	question_text TEXT
);

CREATE TABLE IF NOT EXISTS fill_in_questions(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	question_text TEXT
);

CREATE TABLE IF NOT EXISTS multiple_choice_questions(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	question_text TEXT
);

CREATE TABLE IF NOT EXISTS multiple_choice_choices(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	specific_questionID INT UNSIGNED NOT NULL REFERENCES multiple_choice_questions(id),
	choice VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS picture_response_questions(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	question_text TEXT
);

CREATE TABLE IF NOT EXISTS answers(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	questionID INT UNSIGNED NOT NULL REFERENCES questions(id),
	answer VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS quiz_results(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	quizID INT UNSIGNED NOT NULL REFERENCES quizzes(id),
	userID INT UNSIGNED NOT NULL REFERENCES users(id),
	score INT,
	completion_time INT,
	date_taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
