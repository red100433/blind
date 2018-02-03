package com.school.dao;

import java.util.List;

import com.school.models.vo.Grade;

public class GradeDaoWrapper implements Dao<Grade>{

	@Override
	public List<Grade> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grade getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Grade t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Grade t) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getAllList(String selectOption) {
		// TODO Auto-generated method stub
		return null;
	}

	public Grade getById(int stuId, int subId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(int stuId, int subId) {
		// TODO Auto-generated method stub
		return false;
	}

}
