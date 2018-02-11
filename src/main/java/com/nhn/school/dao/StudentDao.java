package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.model.Student;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface StudentDao {

	 List<Student> findAll();

	 Student getById(int id);

	 boolean save(Student student);

	 boolean delete(int id);
	 
	 int count();
}
