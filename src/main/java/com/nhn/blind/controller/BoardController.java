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

import com.nhn.blind.exception.UserException;
import com.nhn.blind.model.Board;
import com.nhn.blind.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/view")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	WebClient webclient = WebClient.create();

	/**
	 * Mono.fromFutre vs Mono.fromCompletionStage 차이는 일반화 단계인 fromCompletionStage를 써라( api 문서 )
	 * @param model
	 * @return
	 * @throws InterruptedException 
	 */
	@GetMapping("")
	public Mono<String> view(Model model, @CookieValue("userId") String userId) throws InterruptedException {
//		Flux<Board> test = Mono.fromCompletionStage(boardService.getList(id)).flatMapMany(Function.identity()).log();
//		test.subscribe();
		model.addAttribute("userId", userId);
		model.addAttribute("boards", Mono.fromCompletionStage(boardService.getList(-1L)).flatMapMany(Function.identity()));
		return Mono.just("/board/boardListView");
	}
	
	@GetMapping("/{next}")
	@ResponseBody
	public Flux<Board> view(Model model, @PathVariable Long next) throws InterruptedException {
		return Mono.fromCompletionStage(boardService.getList(next)).flatMapMany(Function.identity());
	}

	@GetMapping("/board/{id}")
	// TODO 만약 사용자 userId 와 게시글 작성자의 id 가 맞지 않으면 RuntimeException을 던짐
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
		
//		board.doOnNext(s -> s.setUserId(Integer.parseInt(userId)));
//		log.info("{}", board.block().toString());
		board.setUserId(Integer.parseInt(userId));
		log.info("{}", board.toString());

//		 .map(content -> new BlogPost(UUID.randomUUID(),
//                 content.getTitle(), content.getAuthor(), content.getBody()))
//         .publishOn(Schedulers.parallel())
//         .doOnNext(repository::save)
//         .map(BlogPost::getId);
		 
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
