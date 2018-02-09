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

	 List<Student> getAllList();

	 Student getById(int id);

	 boolean add(Student student);

	 boolean delete(int id);

	 boolean update(Student student);
}
