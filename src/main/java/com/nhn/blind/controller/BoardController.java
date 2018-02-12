package com.nhn.blind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/{id}")
	public Mono<String> board(Model model,@PathVariable Long id) {
		model.addAttribute("boards", boardService.getById(id));
		model.addAttribute("comments", commentService.getBoardCommentById(id));
		return Mono.just("/board/boardView");
	}
	
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
	@ResponseBody
	public Mono<String> addBoard(@RequestBody Board board) {
		log.info("title : {}", board.toString());
		
//		User user = userService.getByEmail(req.getRemoteUser());
//		log.info("user Id : {}", user.getId());
//		log.info("user Name : {}", user.getName());
//		log.info("user Email : {}", user.getEmail());
//		board.setUserId(user.getId());
		
//		boardService.add(board);
		return Mono.just("addBoardSuceess");
	}
	
	@DeleteMapping("/board")
	@ResponseBody
	public Mono<String> deleteUser(@RequestBody Board board) {
		log.info("board Id : {}", board.getId());
//		boardService.delete(board.getId());
		return Mono.just("deleteBoardSuceess");
	}
	
	// change title contents
	@PutMapping("/{id}") 
	public Mono<String> updateUser(Board board) {
		return Mono.just("");
	}
	
}
