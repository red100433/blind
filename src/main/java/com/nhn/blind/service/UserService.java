package com.nhn.blind.service;

import com.nhn.blind.model.User;

import reactor.core.publisher.Mono;

public interface UserService {

	Mono<Void> add(User user);

	Mono<Void> delete(int id);

	Mono<User> getById(int id);

	Mono<User> getByEmail(String email);

}