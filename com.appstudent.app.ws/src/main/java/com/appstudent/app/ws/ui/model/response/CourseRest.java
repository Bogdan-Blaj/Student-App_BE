package com.appstudent.app.ws.ui.model.response;

public class CourseRest {
	
	private String name;
	private int score;
	private String professor;
	private boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public boolean getPassed() {
		return status;
	}

	public void setPassed(boolean status) {
		this.status = status;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

}

