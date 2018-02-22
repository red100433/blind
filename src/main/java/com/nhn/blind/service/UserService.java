package com.nhn.blind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.blind.model.User;
import com.nhn.blind.repository.UserDao;

import reactor.core.publisher.Mono;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public Mono<Void> add(User user) {
		return Mono.just(userDao.add(user)).then();
	}

	public Mono<Void> delete(int id) {
		return Mono.just(userDao.delete(id)).then();
	}

	public Mono<User> getById(int id) {
		return Mono.justOrEmpty(userDao.getById(id));
	}

	public Mono<User> getByEmail(String email) {
		return Mono.justOrEmpty(userDao.getByEmail(email));
	}
}
