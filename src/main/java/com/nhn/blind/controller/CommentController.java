package com.nhn.blind.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhn.blind.model.Comment;
import com.nhn.blind.service.CommentService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/{id}")
	@ResponseBody
	public Flux<Comment> boardComment(@PathVariable Long id) {
		return Mono.fromCompletionStage(commentService.getBoardCommentById(id)).flatMapMany(Function.identity());
	}
	
	@PostMapping("")
	public Flux<Comment> addComment(@RequestBody Comment comment, @CookieValue("userId") String userId) {
		comment.setUserId(Integer.parseInt(userId));
		commentService.add(comment);
		return Mono.fromCompletionStage(commentService.getBoardCommentById(comment.getBoardId())).flatMapMany(Function.identity());
	}
	
	@DeleteMapping("")
	public Flux<Comment> deleteComment(@RequestBody Comment comment, @CookieValue("userId") String userId) {
		comment.setUserId(Integer.parseInt(userId));
		commentService.delete(comment);
		return Mono.fromCompletionStage(commentService.getBoardCommentById(comment.getBoardId())).flatMapMany(Function.identity());
	}
}
