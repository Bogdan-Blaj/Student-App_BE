package com.appstudent.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appstudent.app.ws.io.entity.CourseEntity;
import com.appstudent.app.ws.io.entity.StudentEntity;
import com.appstudent.app.ws.io.repository.CourseRepository;
import com.appstudent.app.ws.io.repository.StudentRepository;
import com.appstudent.app.ws.service.CourseService;
import com.appstudent.app.ws.shared.dto.CourseDTO;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public List<CourseDTO> getCourses(String studentId) {
		List<CourseDTO> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		StudentEntity student = studentRepository.findByStudentId(studentId);
		
		if (student == null) return null;
		
		//obtained student, now get all his courses;
		
		Iterable<CourseEntity> courses = courseRepository.findAllByStudentDetails(student);
		for(CourseEntity courseEntity:courses) {
			
			returnValue.add( modelMapper.map(courseEntity, CourseDTO.class));
		}
		
		
		return returnValue;
	}


}
