package com.appstudent.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appstudent.app.ws.exceptions.StudentServiceException;
import com.appstudent.app.ws.service.StudentService;
import com.appstudent.app.ws.shared.dto.StudentDTO;
import com.appstudent.app.ws.ui.model.request.StudentDetailsRequestModel;
import com.appstudent.app.ws.ui.model.response.ErrorMessages;
import com.appstudent.app.ws.ui.model.response.StudentRest;

@RestController
@RequestMapping("students") //http://localhost:8080/students
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping(produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public StudentRest postStudent(@RequestBody StudentDetailsRequestModel student) {
		StudentRest returnValue = new StudentRest();
		StudentDTO studentDTO = new StudentDTO();
		StudentDTO createdUser = new StudentDTO();
		
		//copy data to DTO
		BeanUtils.copyProperties(student, studentDTO);
		
		//create User
		createdUser = studentService.createStudent(studentDTO); 
		if (createdUser == null) throw new NullPointerException("Could not create user."); //example to show the call of  custom exception 
		
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	
	@GetMapping(path="/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public StudentRest getStudent(@RequestBody String id) {
		StudentRest returnValue = new StudentRest();
		StudentDTO student = studentService.getStudentDetails(id);
		
		BeanUtils.copyProperties(student, returnValue);
		
		return returnValue;
	}
	
	//get student details based on a provided id - done
	
	//update student details
	
	//delete user based on provided id
	
	//add course list
	
	//add course based on userID
	
	//show all courses for a user based on Id
	
	//show only passed course for a user
	
	//implement security
	
	//filters and login
	
	//documentation in swagger
	
	//create roles ( professor and rights to change )
	
	//if professor has coresponding Id of a course, change mark for a student
	//professorID, courseId, studentId, newMark
	
	
//	@PutMapping(produces= {
//			MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE
//	})
//	public StudentRest putStudent(@RequestBody StudentDetailsRequestModel student) {
//		StudentRest returnValue = new StudentRest();
//		BeanUtils.copyProperties(student, returnValue);
//		return returnValue;
//	}
//	
//	@GetMapping(produces= {
//			MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE
//	})
//	public StudentRest getStudent(@RequestBody StudentDetailsRequestModel student) {
//		StudentRest returnValue = new StudentRest();
//		BeanUtils.copyProperties(student, returnValue);
//		return returnValue;
//	}
//	
//	@DeleteMapping(produces= {
//			MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE
//	})
//	public StudentRest deleteStudent(@RequestBody StudentDetailsRequestModel student) {
//		StudentRest returnValue = new StudentRest();
//		BeanUtils.copyProperties(student, returnValue);
//		return returnValue;
//	}
//	
}
