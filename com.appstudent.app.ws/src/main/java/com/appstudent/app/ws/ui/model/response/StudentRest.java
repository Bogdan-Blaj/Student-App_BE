package com.appstudent.app.ws.ui.model.response;

import java.util.List;

public class StudentRest {

	private String name;
	private String firstName;
	private String email;
	private int age;
	private String studentId;
	private int studyYear;
	private List<CourseRest> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	public List<CourseRest> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseRest> courses) {
		this.courses = courses;
	}

	
}
