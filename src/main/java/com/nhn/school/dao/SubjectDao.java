package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.models.vo.Subject;

/**
 *
 * @author daeyun-jang
 *
 */

@Mapper
public interface SubjectDao {

	 List<Subject> getAllList();

	 Subject getById(int id);

	 boolean add(Subject subject);

	 boolean delete(int id);

	 boolean update(Subject subject);
}
