package com.appstudent.app.ws.ui.model.request;

import java.util.List;

public class StudentDetailsRequestModel {
	private String name;
	private String firstName;
	private String email;
	private String password;
	private int age;
	private int studyYear;
	private List<CourseRequestModel> courses;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	public List<CourseRequestModel> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseRequestModel> courses) {
		this.courses = courses;
	}

}
