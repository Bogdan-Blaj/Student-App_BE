package com.appstudent.app.ws.service;

import com.appstudent.app.ws.shared.dto.StudentDTO;

public interface StudentService {
	StudentDTO createStudent(StudentDTO student);
	StudentDTO getStudentDetails(String id);
}
