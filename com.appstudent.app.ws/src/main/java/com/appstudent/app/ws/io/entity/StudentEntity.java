package com.appstudent.app.ws.io.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="students")
public class StudentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -553696979631321998L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String studentId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String FirstName;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}