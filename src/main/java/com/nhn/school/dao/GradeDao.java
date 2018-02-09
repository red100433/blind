package com.nhn.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.school.dao.custom.GradeDaoWrapper;
import com.nhn.school.db.DBConnection;
import com.nhn.school.models.Type;
import com.nhn.school.models.vo.Grade;

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
