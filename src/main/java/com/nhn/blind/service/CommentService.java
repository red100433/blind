package com.nhn.blind.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.model.Comment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {

	/**
	 * 만약 실패하면 Mono.error 에 Exception을 담아 리턴한다.
	 * @param board
	 * @return
	 */
	Mono<Boolean> add(Comment comment);

	/**
	 * 만약 실패하면 Mono.error 에 Exception을 담아 리턴한다.
	 * @param board
	 * @return
	 */
	Mono<Boolean> delete(Comment comment);

	/**
	 * commentDao.getCommentById가 3번 fail 되면 RuntimeException을날림
	 * @param boardId
	 * @return
	 */
	CompletableFuture<Flux<Comment>> getBoardCommentById(Long boardId);

}