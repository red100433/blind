package com.nhn.blind.repository;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.User;

@Mapper
public interface UserDao {
	boolean add(User user);

	boolean delete(int id);

	User getById(int id);

	User getByEmail(String email);
}
