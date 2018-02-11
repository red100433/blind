package com.nhn.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.model.Grade;

/**
 *
 * @author daeyun-jang
 *
 */
@Mapper
public interface GradeDao {

	List<Grade> findAll();
	List<Grade> findAvgStudent();
	List<Grade> findAvgSubject();

	Grade getById(int stuId, int subId);

	boolean save(Grade grade);

	boolean delete(int id);

}
