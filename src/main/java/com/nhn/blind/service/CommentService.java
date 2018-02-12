package com.nhn.blind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.blind.model.Comment;
import com.nhn.blind.repository.CommentDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;

	public Flux<Comment> getList() {
		return Flux.fromIterable(commentDao.getList());
	}
	
	public boolean add(Comment comment) {
		return commentDao.add(comment);
	}
	public boolean update(Comment comment) {
		return commentDao.update(comment);
	}
	public boolean delete(Long id) {
		return commentDao.delete(id);
	}
	public Flux<Comment> getBoardCommentById(Long boardId) {
		return Flux.fromIterable(commentDao.getBoardCommentById(boardId));
	}
	
	public Mono<Comment> getBoardCommentByIdOne(Long boardId) {
		return Mono.just(commentDao.getBoardCommentByIdOne(boardId));
	}
	
}
