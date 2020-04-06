package com.appstudent.app.ws.service;

import java.util.List;

import com.appstudent.app.ws.shared.dto.CourseDTO;

public interface CourseService {
	List<CourseDTO> getCourses(String studentId);
}
