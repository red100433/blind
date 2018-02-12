package com.nhn.blind.repository;

import org.apache.ibatis.annotations.Mapper;

import com.nhn.blind.model.Role;

@Mapper
public interface RoleDao {
	boolean add(String email);
	boolean update(Role role);
	boolean delete(String email);
	Role getByEmail(String email);
}