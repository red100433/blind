package com.nhn.blind.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.cache.BoardCache;
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

	@Autowired
	private BoardCache boardCache;

	/**
	 * boardDao.getList가 3번 fail 되면 runtimeException을날림
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@Async
	public CompletableFuture<Flux<Board>> getList(Long next) throws InterruptedException {
		if (next.equals(-1L) | next.compareTo(boardCache.getLastIndexBoardId()) > 0) {
			log.info("cache data");
			return CompletableFuture.completedFuture(Flux.fromIterable(boardCache.findBoardGroup(next)).retry(3))
					.exceptionally(e -> {
						throw new RuntimeException(e.getMessage());
					});
		} else {
			log.info("DB data");
			return CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList(next)).retry(3))
					.exceptionally(e -> {
						throw new RuntimeException(e.getMessage());
					});
		}
		// if (-1L == next) {
		// next = boardDao.getLastBoardId()+1;
		// }
		// return
		// CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList(next)).retry(3))
		// .exceptionally(e -> {
		// throw new RuntimeException(e.getMessage());
		// });
	}

	public int getCount() {
		return boardDao.getCount();
	}

	@Transactional
	@Async
	public Mono<Boolean> add(Board board) {
		boolean cacheFlag = false;
		if(board.getId() != null & board.getId() != 0) {
			cacheFlag = boardCache.changeBoard(board);
		} else {
			cacheFlag = boardCache.addBoard(board);
		}
		
		if (boardDao.add(board) & cacheFlag) {
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new RuntimeException()));
		}
	}

	@Transactional
	public Mono<Boolean> delete(Board board) {
		// Mono<Boolean> justOrEmpty = Mono.justOrEmpty(boardDao.delete(board)).retry(3)
		// .switchIfEmpty(Mono.defer(() -> Mono.error(new UserException("No Access
		// User!!!!!"))));
		if (boardDao.delete(board) & boardCache.deleteBoard(board)) {
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!")));
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
		return Mono.justOrEmpty(boardDao.getById(id, userId)).retry(3)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!"))));
	}
}
