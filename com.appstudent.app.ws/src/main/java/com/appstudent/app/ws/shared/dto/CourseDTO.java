package com.appstudent.app.ws.shared.dto;

import java.io.Serializable;

import com.appstudent.app.ws.ui.model.response.CourseStatus;

public class CourseDTO implements Serializable {
	private static final long serialVersionUID = 7100378081492278587L;

	private String id;
	private String studentPublicId;
	private String name;
	private String professor;
	private int studyYear;
	private int score;
	private boolean status;
	private StudentDTO studentDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStudentPublicId() {
		return studentPublicId;
	}

	public void setStudentPublicId(String studentPublicId) {
		this.studentPublicId = studentPublicId;
	}

	public StudentDTO getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDTO studentDetails) {
		this.studentDetails = studentDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
