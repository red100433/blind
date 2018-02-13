package com.nhn.blind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.nhn.blind.model.Board;
import com.nhn.blind.model.Comment;
import com.nhn.blind.service.BoardService;
import com.nhn.blind.service.CommentService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/view")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;
	
	WebClient webclient = WebClient.create();
	
	@GetMapping("")
	public Mono<String> view(Model model) {
		Flux<Board> board = boardService.getList();
		model.addAttribute("boards", board);
		return Mono.just("/board/boardListView");
	}
	
//	@GetMapping("/{id}")
//	public Mono<String> board(Model model,@PathVariable Long id) {
//		model.addAttribute("boards", boardService.getById(id));
//		model.addAttribute("comments", commentService.getBoardCommentById(id));
//		return Mono.just("/board/boardView");
//	}
	
	@GetMapping("/board/{id}")
	@ResponseBody
	public Flux<Comment> boardComment(@PathVariable Long id) {
		log.info("BoardComment GetMapping and Id: {}", id);
		return commentService.getBoardCommentById(id);
	}
	
	@GetMapping("/board")
	public String addView() {
		return "/board/addBoard";
	}
	
	@PostMapping("/board")
	public Mono<String> addBoard(@RequestBody Board board, @CookieValue("userId") String userId) {
		log.info("title : {}", board.toString());
		board.setUserId(Integer.parseInt(userId));
		
		boardService.add(board);
		return Mono.just("redirect:/view");
	}
	
	@DeleteMapping("/board")
	@ResponseBody
	public Mono<String> deleteBoard(@RequestBody Board board, @CookieValue("userId") String userId) {
		log.info("board Id : {}", board.toString());
		log.info("user Id : {}", userId);
		board.setUserId(Integer.parseInt(userId));
		log.info("board Id : {}", board.toString());
		boardService.delete(board);
		return Mono.just("deleteBoardSuceess");
	}
	
	// change title contents
	@PutMapping("/board")
	public Mono<String> updateBoard(Model model, @RequestBody Board board, @CookieValue("userId") String userId) {
		board.setUserId(Integer.parseInt(userId));
		log.info("{}", board.toString());
		model.addAttribute("board", boardService.getById(board));
		return Mono.just("/board/addBoard");
	}
	
}
