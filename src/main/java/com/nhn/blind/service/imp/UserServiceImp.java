package com.nhn.blind.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.model.User;
import com.nhn.blind.repository.UserDao;
import com.nhn.blind.service.UserService;

import reactor.core.publisher.Mono;

/**
 * 
 * @author daeyun.jang
 *
 */
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Mono<Void> add(User user) {
		return Mono.just(userDao.add(user)).then();
	}

	@Override
	@Transactional
	public Mono<Void> delete(int id) {
		return Mono.just(userDao.delete(id)).then();
	}

	@Override
	public Mono<User> getById(int id) {
		return Mono.justOrEmpty(userDao.getById(id));
	}

	@Override
	public Mono<User> getByEmail(String email) {
		return Mono.justOrEmpty(userDao.getByEmail(email));
	}
}
