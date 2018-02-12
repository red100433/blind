package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhn.blind.model.Comment;
import com.nhn.blind.service.CommentService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping(value = "")
	@ResponseBody
	public Mono<Comment> addComment(@RequestBody Comment comment, @CookieValue("userId") String userId) {
		log.info("{}", comment.toString());
		log.info("cookie userId : {}", userId);
		comment.setUserId(Integer.parseInt(userId));
		commentService.add(comment);
		return commentService.getBoardCommentByIdOne(comment.getBoardId());
	}
}
