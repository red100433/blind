package com.nhn.blind.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.model.Board;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardService {

	/**
	 * boardDao.getList가 3번 fail 되면 runtimeException을날림
	 * Cache에 데이터가 있으면 값을 리턴해주고 없으면 DB에 접근하여 값을 리턴한다.
	 * @return
	 */
	CompletableFuture<Flux<Board>> getList(Long next);

	/**
	 * 만약 실패하면 Mono.error 에 Exception을 담아 리턴한다.
	 * @param board
	 * @return
	 */
	Mono<Boolean> add(Board board);

	/**
	 * 만약 실패하면 Mono.error 에 Exception을 담아 리턴한다.
	 * @param board
	 * @return
	 */
	Mono<Boolean> delete(Board board);

	/**
	 * boardDao.getById가 3번 fail 되면 RuntimeException을 날림
	 * @param id
	 * @param userId
	 * @return
	 */
	Mono<Board> getById(Long id, int userId);

}