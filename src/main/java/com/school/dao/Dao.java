package com.school.dao;

import java.util.List;

public interface Dao<T> {
	List<T> getAllList();
	T getById(int id);
	boolean add(T t);
	boolean delete(int id);
	boolean update(T t);
}
