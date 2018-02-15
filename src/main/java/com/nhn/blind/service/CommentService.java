package com.nhn.blind.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.model.Comment;
import com.nhn.blind.repository.CommentDao;

import reactor.core.publisher.Flux;

@Service
@EnableAsync
public class CommentService {
	@Autowired
	private CommentDao commentDao;

	public Flux<Comment> getList() {
		return Flux.fromIterable(commentDao.getList());
	}
	
	@Transactional
	public boolean add(Comment comment) {
		return commentDao.add(comment);
	}
	@Transactional
	public boolean update(Comment comment) {
		return commentDao.update(comment);
	}
	@Transactional
	public boolean delete(Comment comment) {
		return commentDao.delete(comment);
	}
	
	/**
	 * commentDao.getCommentById가 3번 fail 되면 runtimeException을날림
	 * @param boardId
	 * @return
	 */
	@Async
	public CompletableFuture<Flux<Comment>> getBoardCommentById(Long boardId) {
		return CompletableFuture.completedFuture(Flux.fromIterable(commentDao.getBoardCommentById(boardId)).retry(3)).exceptionally(e -> {
			throw new RuntimeException(e.getMessage());
		});
	}
	
//	public Mono<Comment> getBoardCommentByIdOne(Long boardId) {
//		return Mono.just(commentDao.getBoardCommentByIdOne(boardId));
//	}
	
}
