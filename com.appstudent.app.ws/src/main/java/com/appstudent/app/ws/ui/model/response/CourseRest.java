package com.appstudent.app.ws.ui.model.response;

public class CourseRest {
	
	private String name;
	private String score;
	private String teacher;
	private CourseStatus passed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public CourseStatus getPassed() {
		return passed;
	}

	public void setPassed(CourseStatus passed) {
		this.passed = passed;
	}

}

