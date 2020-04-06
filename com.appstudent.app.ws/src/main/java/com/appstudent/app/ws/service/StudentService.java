package com.appstudent.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appstudent.app.ws.shared.dto.StudentDTO;

public interface StudentService extends UserDetailsService{
	StudentDTO createStudent(StudentDTO student);
	StudentDTO getStudentDetails(String id);
	StudentDTO updateStudentNames(String id, StudentDTO student);
	StudentDTO deleteStudent(String id);
	StudentDTO getStudent(String email);
}
