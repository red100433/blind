package com.nhn.blind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.blind.model.User;
import com.nhn.blind.repository.UserDao;

import reactor.core.publisher.Mono;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public List<User> getUserList() {
		return userDao.getList();
	}

	public Mono<Boolean> add(User user) {
		return Mono.just(userDao.add(user));
	}

	public Mono<Boolean> update(User user) {
		return Mono.just(userDao.update(user));
	}

	public Mono<Boolean> delete(int id) {
		return Mono.just(userDao.delete(id));
	}

	public Mono<User> getById(int id) {
		return Mono.justOrEmpty(userDao.getById(id));
	}

	public Mono<User> getByEmail(String email) {
		return Mono.justOrEmpty(userDao.getByEmail(email));
	}
}
