package com.appstudent.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appstudent.app.ws.exceptions.StudentServiceException;
import com.appstudent.app.ws.service.CourseService;
import com.appstudent.app.ws.service.StudentService;
import com.appstudent.app.ws.shared.dto.CourseDTO;
import com.appstudent.app.ws.shared.dto.StudentDTO;
import com.appstudent.app.ws.ui.model.request.StudentDetailsRequestModel;
import com.appstudent.app.ws.ui.model.request.StudentNamesUpdateModel;
import com.appstudent.app.ws.ui.model.response.CourseRest;
import com.appstudent.app.ws.ui.model.response.ErrorMessages;
import com.appstudent.app.ws.ui.model.response.StudentRest;

@RestController
@RequestMapping("students") //http://localhost:8080/students
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@PostMapping(produces= {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public StudentRest postStudent(@RequestBody StudentDetailsRequestModel student) {
		StudentRest returnValue = new StudentRest();
		StudentDTO studentDTO = new StudentDTO();
		StudentDTO createdUser = new StudentDTO();
		
		//copy data to DTO
		//BeanUtils.copyProperties(student, studentDTO);
		ModelMapper modelMapper = new ModelMapper();
		studentDTO = modelMapper.map(student, StudentDTO.class);
		
		//create User
		createdUser = studentService.createStudent(studentDTO); 
		if (createdUser == null) throw new NullPointerException("Could not create user."); //example to show the call of  custom exception 
		
//		BeanUtils.copyProperties(createdUser, returnValue);
		returnValue = modelMapper.map(createdUser, StudentRest.class);
		return returnValue;
	}
	
	@GetMapping(path="/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public StudentRest getStudent(@PathVariable String id) {
		StudentRest returnValue = new StudentRest();
		StudentDTO student = studentService.getStudentDetails(id);
		
//		BeanUtils.copyProperties(student, returnValue);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(student, StudentRest.class);
		
		return returnValue;
	}
	
	@GetMapping
	public String testGet() {
		return new String ("Get was called");
	}
	
	@GetMapping(path="/{id}/courses" , produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public List<CourseRest> getStudentCourses(@PathVariable String id) {
		List<CourseRest> returnValue = new ArrayList<>();
		
		List<CourseDTO> courses = courseService.getCourses(id);
		
		if (courses != null && !courses.isEmpty()) {
			
			java.lang.reflect.Type listType = new TypeToken<List<CourseRest>>() {}.getType();
			ModelMapper modelMapper = new ModelMapper();
			returnValue = modelMapper.map(courses, listType);
			
		}
		
		
		return returnValue;
	}
	
	
	@PutMapping( path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public StudentRest UpdateStudentName(@PathVariable String id, @RequestBody StudentNamesUpdateModel student) {
		StudentRest returnValue = new StudentRest();
		StudentDTO updatedValue = new StudentDTO();
		StudentDTO updatedUser = new StudentDTO();
		
		if (id == null || student == null) 
			throw new StudentServiceException(ErrorMessages.INSUFICIENT_DATA_PROVIDED.getErrorMessage());
		
//		BeanUtils.copyProperties(student, updatedValue);
		ModelMapper modelMapper = new ModelMapper();
		updatedValue = modelMapper.map(student, StudentDTO.class);
		
		updatedUser = studentService.updateStudentNames(id, updatedValue);
//		BeanUtils.copyProperties(updatedUser, returnValue);
		returnValue = modelMapper.map(updatedUser, StudentRest.class);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public String DeleteStudent(@PathVariable String id) {
		StudentRest returnValue = new StudentRest();
		StudentDTO deletedUser = new StudentDTO();
		if (id == null) 
			throw new StudentServiceException(ErrorMessages.INSUFICIENT_DATA_PROVIDED.getErrorMessage());
		
		deletedUser = studentService.deleteStudent(id);
		if (deletedUser.getName() != null)
			return new String("User with id = " + id + " was deleted");
		else
			return new String("No user was found");
	}
}
	
	
	
	//get student details based on a provided id - done
	
	//update a students name => provide Id => Service.updateName => if exists , change, save, return entry to user - done
	
	//delete user based on provided id => Service.delete => if exists, delete ( similar to createStudent) - done

	// see if you should use BeanUtils or something else for changing the data. - done

	//add Model Mapper support - done
	
	//add course list => see course for help - done

	//show all courses for a user based on Id - done

	//get list of courses by providing studentId - done

	//implement security - done

	//filters and login  - done

	//let access to the public methods if header is valid (service endpoint) - done

	//__________________________________________

	//look into JPA for the link between students and courses - analysis

	//look into the Spring Security - analysis

	//_________________________________________
	
	//show only passed course for a user - sql native

	//show all students of a course  - sql native 
	// for this: 
	// change tables: 
	// 1 table for students -remain the same
	// 1 table for courses  - course data + course_id
	// 1 table for links student_id course_id
	// link between the 2 id in the links table

	//option to add students with or without courses - Skipped 

	//add course based on userID - Skipped 

	//create roles ( professor and rights to change )
	
	//professor with basic data and owner of specific courses ( course_id) 1 to many

	//if professor has corresponding Id of a course, change mark for a student
	//professorID, courseId, studentId, newMark
	
	//documentation in swagger
