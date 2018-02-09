package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.models.vo.Teacher;

@Mapper
public interface TeacherDao {
	List<Teacher> getAllList();

	Teacher getById(int id);

	boolean add(Teacher teacher);

	boolean delete(int id);

	boolean update(Teacher teacher);
}
