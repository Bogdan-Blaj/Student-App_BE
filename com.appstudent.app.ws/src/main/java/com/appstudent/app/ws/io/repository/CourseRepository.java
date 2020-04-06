package com.appstudent.app.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appstudent.app.ws.io.entity.CourseEntity;
import com.appstudent.app.ws.io.entity.StudentEntity;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long>{
	List<CourseEntity> findAllByStudentDetails(StudentEntity studentEntity);

}
