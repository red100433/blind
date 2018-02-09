package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.model.Subject;

/**
 *
 * @author daeyun-jang
 *
 */

@Mapper
public interface SubjectDao {

	List<Subject> findAll();

	Subject getById(int id);

	boolean save(Subject subject);

	boolean delete(int id);

}
