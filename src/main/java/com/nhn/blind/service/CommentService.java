package com.nhn.blind.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.cache.CommentCache;
import com.nhn.blind.model.Comment;
import com.nhn.blind.repository.CommentDao;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@EnableAsync
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private CommentCache commentCache;
	
	@Transactional
	@Async(value = "myCommentThreadPool")
	public Mono<Boolean> add(Comment comment) {
		if(commentDao.add(comment)) {
			commentCache.changeComment(comment.getBoardId());
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new RuntimeException()));
		}
	}
	
	@Transactional
	@Async(value = "myCommentThreadPool")
	public Mono<Boolean> delete(Comment comment) {
		if(commentDao.delete(comment)) {
			commentCache.changeComment(comment.getBoardId());
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new RuntimeException()));
		}
//		return Mono.just(commentDao.delete(comment));
	}
	
	/**
	 * commentDao.getCommentById가 3번 fail 되면 runtimeException을날림
	 * @param boardId
	 * @return
	 */
	@Async(value = "myCommentThreadPool")
	public CompletableFuture<Flux<Comment>> getBoardCommentById(Long boardId) {
		return CompletableFuture.completedFuture(Flux.fromIterable(commentCache.findCommentGroup(boardId)).retry(3)).exceptionally(e -> {
			throw new RuntimeException(e.getMessage());
		});
	}
	
//	public Mono<Comment> getBoardCommentByIdOne(Long boardId) {
//		return Mono.just(commentDao.getBoardCommentByIdOne(boardId));
//	}
	
}
