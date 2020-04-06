package com.appstudent.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appstudent.app.ws.ui.model.response.CourseStatus;

@Entity(name="courses")
public class CourseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6066170573966163634L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String studentPublicId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String professor;
	
	@Column(nullable = false)
	private int studyYear;
	
	@Column(nullable = false)
	private int score;
	
	@Column(nullable = false)
	private boolean status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentEntity studentDetails;


	public StudentEntity getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentEntity studentDetails) {
		this.studentDetails = studentDetails;
	}

	public String getStudentPublicId() {
		return studentPublicId;
	}

	public void setStudentPublicId(String studentPublicId) {
		this.studentPublicId = studentPublicId;
	}

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
}
