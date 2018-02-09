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

	List<String> getAllList(String selectOption);

	Grade getById(int stuId, int subId);

	boolean add(Grade grade);

	boolean delete(int stuId, int subId);

	boolean update(Grade grade);

}
