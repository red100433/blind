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

@Controller
//@RestController
@RequestMapping("/view")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	WebClient webclient = WebClient.create();

	@GetMapping("")
	public Mono<String> view(Model model) throws InterruptedException {
		// CompletableFuture<Flux<Board>> board = boardService.getList();
		// Flux<Board> fromCompletionStage =
		// Mono.fromCompletionStage(boardService.getList()).block();

		// Mono.block -> blocking i.o임 .block을 하는 순간 부하테스트 통과하지 못함.
		// model.addAttribute("boards",
		// Mono.fromCompletionStage(boardService.getList()));
		// return Mono.just("/board/boardListView");

//		Mono<List<Board>> fromCompletionStage = Mono.fromCompletionStage(boardService.getList());
//		Flux<Board> test = fromCompletionStage.flatMapMany(s -> Flux.fromIterable(s));
		Flux<Board> test = Mono.fromCompletionStage(boardService.getList()).flatMapMany(Function.identity());
		model.addAttribute("boards", test);
		return Mono.just("/board/boardListView");
	}

	@GetMapping("/{id}")
	// TODO 만약 사용자 userId 와 게시글 작성자의 id 가 맞지 않으면 RuntimeException을 던짐, 아직 예외 처리 안함
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
		log.info("title : {}", board.toString());

		boardService.add(board);
		return Mono.just("redirect:/view");
	}

	@DeleteMapping("/board")
	@ResponseBody
	// TODO 게시글 삭제시 댓글도 다 지워야할지 고민
	public Mono<String> deleteBoard(@RequestBody Board board, @CookieValue("userId") String userId) {
		board.setUserId(Integer.parseInt(userId));
		log.info("board Id : {}", board.toString());
		boardService.delete(board);

		return Mono.just("deleteBoardSuceess");
	}

}
