package com.nhn.blind.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.User;

@Mapper
public interface UserDao {
	List<User> getList();
	boolean add(User user);
	boolean update(User user);
	boolean delete(int id);
	User getById(int id);
	User getByEmail(String email);
}
