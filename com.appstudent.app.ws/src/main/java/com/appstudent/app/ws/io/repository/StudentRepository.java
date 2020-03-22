package com.appstudent.app.ws.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appstudent.app.ws.io.entity.StudentEntity;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long>{
	StudentEntity findByEmail(String email);
	StudentEntity findByStudentId(String id);
}
