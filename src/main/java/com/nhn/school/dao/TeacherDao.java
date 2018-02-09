package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.model.Teacher;

@Mapper
public interface TeacherDao {
	List<Teacher> findAll();

	Teacher getById(int id);

	boolean save(Teacher teacher);

	boolean delete(int id);

	boolean save(Teacher teacher, int id);
}
