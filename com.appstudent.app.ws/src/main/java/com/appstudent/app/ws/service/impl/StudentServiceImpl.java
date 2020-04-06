package com.appstudent.app.ws.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appstudent.app.ws.exceptions.StudentServiceException;
import com.appstudent.app.ws.io.entity.StudentEntity;
import com.appstudent.app.ws.io.repository.StudentRepository;
import com.appstudent.app.ws.service.StudentService;
import com.appstudent.app.ws.shared.Utils;
import com.appstudent.app.ws.shared.dto.CourseDTO;
import com.appstudent.app.ws.shared.dto.StudentDTO;
import com.appstudent.app.ws.ui.model.response.ErrorMessages;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public StudentDTO createStudent(StudentDTO student) {
		
		StudentEntity storedStudentDetails = studentRepository.findByEmail(student.getEmail());
		
		if ( storedStudentDetails != null ) throw new StudentServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage()); 


		StudentEntity studentEntity = new StudentEntity();
		
//		BeanUtils.copyProperties(student, studentEntity);
		String generatedId = utils.generateStudentId(30);
		if (student.getCourses().size() > 0) {
			for (int i =0; i< student.getCourses().size(); i++) {
				CourseDTO course = student.getCourses().get(i);
				course.setStudentDetails(student);
				course.setStudentPublicId(generatedId);
				course.setStatus(false);
				student.getCourses().set(i, course);
				
			}
		}
		

		ModelMapper modelMapper = new ModelMapper();
		studentEntity = modelMapper.map(student, StudentEntity.class);

		studentEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		studentEntity.setStudentId(generatedId);
		
		storedStudentDetails = studentRepository.save(studentEntity);
		StudentDTO returnValue = new StudentDTO();
		
		BeanUtils.copyProperties(storedStudentDetails, returnValue);
		returnValue = modelMapper.map(storedStudentDetails, StudentDTO.class);
		
		return returnValue;
	}

	@Override
	public StudentDTO getStudentDetails(String id) {
		StudentDTO returnValue = new StudentDTO();
		StudentEntity storedUser =  studentRepository.findByStudentId(id);
		
		if (storedUser == null) throw new StudentServiceException(ErrorMessages.INVALID_USER_REQUEST.getErrorMessage());
		
		BeanUtils.copyProperties(storedUser, returnValue);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(storedUser, StudentDTO.class);
		
		return returnValue;
	}

	@Override
	public StudentDTO updateStudentNames(String id, StudentDTO student) {
		StudentDTO returnValue = new StudentDTO();
		StudentEntity storedUser =  studentRepository.findByStudentId(id);	
		
		//search by Id
		if (storedUser == null) throw new StudentServiceException(ErrorMessages.INVALID_USER_REQUEST.getErrorMessage());

		//if id exists, update name and firstName
		storedUser.setFirstName(student.getFirstName());
		storedUser.setName(student.getName());
		
		StudentEntity updatedStudent = studentRepository.save(storedUser);
		
		//return values
//		BeanUtils.copyProperties(updatedStudent, returnValue);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(updatedStudent, StudentDTO.class);
		return returnValue;
	}

	@Override
	public StudentDTO deleteStudent(String id) {
		StudentEntity storedUser =  studentRepository.findByStudentId(id);	
		StudentDTO returnValue = new StudentDTO();
		
		//search by Id
		if (storedUser == null) return returnValue;
		
		studentRepository.deleteById(storedUser.getId());
		BeanUtils.copyProperties(storedUser, returnValue);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(storedUser, StudentDTO.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		StudentEntity studentEntity = studentRepository.findByEmail(email);
		
		if(studentEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(studentEntity.getEmail(), studentEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public StudentDTO getStudent(String email) {
		StudentEntity studentEntity = studentRepository.findByEmail(email);
		StudentDTO returnValue = new StudentDTO();
		
		if(studentEntity == null) throw new UsernameNotFoundException(email);

		BeanUtils.copyProperties(studentEntity, returnValue);
		
		return returnValue;
	}

}
