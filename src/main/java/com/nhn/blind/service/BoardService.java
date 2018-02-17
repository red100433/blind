package com.nhn.blind.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.exception.UserException;
import com.nhn.blind.model.Board;
import com.nhn.blind.repository.BoardDao;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@EnableAsync
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	/**
	 * boardDao.getList가 3번 fail 되면 runtimeException을날림
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	@Async
	public CompletableFuture<Flux<Board>> getList(Long next) throws InterruptedException {
//		Thread.sleep(2000);
		if (-1L == next) {
			next = boardDao.getLastBoardId()+1;
		}
		return CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList(next)).retry(3))
				.exceptionally(e -> {
					throw new RuntimeException(e.getMessage());
				});
	}

	public int getCount() {
		return boardDao.getCount();
	}

	@Transactional
	@Async
	public void add(Board board) {

//		try {
//
//			for (int i = 4; i < 100; i++) {
//				String title = "test" + i;
//				String content = i + "번째 테스트입니다";
//				Board dummy = new Board();
//				dummy.setTitle(title);
//				dummy.setContent(content);
//				dummy.setUserId(1);
//				boardDao.add(dummy);
//				Thread.sleep(100);
//				log.info("돌고 있습니다 : {}", i);
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 boardDao.add(board);
	}

	@Transactional
	public boolean update(Board board) {
		return boardDao.update(board);
	}

	@Transactional
	public boolean delete(Board board) {
		if(boardDao.delete(board)) {
			return true;
		} else {
			throw new UserException("No Access User!!!!!");
		}
	}

	/**
	 * boardDao.getById가 3번 fail 되면 RuntimeException을 날림
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public Mono<Board> getById(Long id, int userId) {
		// Mono<ServerResponse> error = ServerResponse.notFound().build();
		// Mono<Board> justOrEmpty = Mono.justOrEmpty(boardDao.getById(id, userId));
		// return Mono.justOrEmpty(boardDao.getById(id, userId));
		return Mono.justOrEmpty(boardDao.getById(id, userId))
				.retry(3)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!"))))
				;
	}
}
