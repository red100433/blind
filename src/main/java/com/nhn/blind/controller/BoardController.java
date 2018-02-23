package com.nhn.blind.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.nhn.blind.model.Board;
import com.nhn.blind.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author daeyun.jang
 *
 */
@Controller
@RequestMapping("/view")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	WebClient webclient = WebClient.create();

	/**
	 * Mono.fromFutre 와 Mono.fromCompletionStage 차이는 일반화 단계인 fromCompletionStage를 쓰라고함( api 문서 )
	 * @param model
	 * @param userId
	 * @return
	 */
	@GetMapping
	public Mono<String> view(Model model, @CookieValue("userId") String userId) {
		model.addAttribute("userId", userId);
		model.addAttribute("boards",
			Mono.fromCompletionStage(boardService.getList(-1L)).flatMapMany(Function.identity()));
		return Mono.just("/board/boardListView");
	}

	@GetMapping("/{next}")
	@ResponseBody
	public Flux<Board> view(Model model, @PathVariable Long next) {
		return Mono.fromCompletionStage(boardService.getList(next)).flatMapMany(Function.identity());
	}

	/**
	 * 만약 사용자 userId 와 게시글 작성자의 id 가 맞지 않으면 UserException을 던짐
	 * @param model
	 * @param id
	 * @param userId
	 * @return
	 */
	@GetMapping("/board/{id}")
	public Mono<String> board(Model model, @PathVariable Long id, @CookieValue("userId") String userId) {
		model.addAttribute("board", boardService.getById(id, Integer.parseInt(userId)));
		return Mono.just("/board/addBoard");
	}

	@GetMapping("/board")
	public Mono<String> addView(Model model) {
		model.addAttribute("board", new Board());
		return Mono.just("/board/addBoard");
	}

	@PostMapping("/board")
	public Mono<String> addBoard(@RequestBody Board board, @CookieValue("userId") String userId) {
		board.setUserId(Integer.parseInt(userId));
		boardService.add(board);
		return Mono.just("redirect:/view");
	}

	@DeleteMapping("/board")
	@ResponseBody
	public Mono<Boolean> deleteBoard(@RequestBody Board board, @CookieValue("userId") String userId) {
		board.setUserId(Integer.parseInt(userId));
		return boardService.delete(board);
	}

}
