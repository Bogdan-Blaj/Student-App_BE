package com.appstudent.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appstudent.app.ws.exceptions.StudentServiceException;
import com.appstudent.app.ws.io.entity.StudentEntity;
import com.appstudent.app.ws.io.repository.StudentRepository;
import com.appstudent.app.ws.service.StudentService;
import com.appstudent.app.ws.shared.Utils;
import com.appstudent.app.ws.shared.dto.StudentDTO;
import com.appstudent.app.ws.ui.model.response.ErrorMessages;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	Utils utils;

	@Override
	public StudentDTO createStudent(StudentDTO student) {
		
		StudentEntity storedStudentDetails = studentRepository.findByEmail(student.getEmail());
		
		if ( storedStudentDetails != null ) throw new StudentServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage()); 

		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(student, studentEntity);
		
		studentEntity.setEncryptedPassword("test");
		studentEntity.setStudentId(utils.generateUserId(30));
		
		storedStudentDetails = studentRepository.save(studentEntity);
		StudentDTO returnValue = new StudentDTO();
		
		BeanUtils.copyProperties(storedStudentDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public StudentDTO getStudentDetails(String id) {
		StudentDTO returnValue = new StudentDTO();
		StudentEntity storedUser =  studentRepository.findByStudentId(id);
		
		if (storedUser == null) throw new StudentServiceException(ErrorMessages.INVALID_USER_REQUEST.getErrorMessage());
		
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

}
