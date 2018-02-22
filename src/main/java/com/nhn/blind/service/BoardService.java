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
	 * Cache에 데이터가 있으면 값을 리턴해주고 없으면 DB에 접근하여 값을 리턴한다.
	 * @return
	 */
	@Async(value = "myBoardThreadPool")
	public CompletableFuture<Flux<Board>> getList(Long next) {
		if (next.equals(-1L) | next.compareTo(boardCache.getLastIndexBoardId()) > 0) {
			return CompletableFuture.completedFuture(Flux.fromIterable(boardCache.findGroup(next)).retry(3))
				.exceptionally(e -> {
					throw new RuntimeException(e.getMessage());
				});
		}
		return CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList(next)).retry(3))
			.exceptionally(e -> {
				throw new RuntimeException(e.getMessage());
			});

	}

	@Transactional
	public Mono<Boolean> add(Board board) {
		if (boardDao.add(board)) {
			boardCache.changeBoard();
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new RuntimeException()));
		}
	}

	@Transactional
	public Mono<Boolean> delete(Board board) {
		if (boardDao.delete(board)) {
			boardCache.deleteBoard(board);
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!")));
		}
	}

	/**
	 * boardDao.getById가 3번 fail 되면 RuntimeException을 날림
	 * @param id
	 * @param userId
	 * @return
	 */
	public Mono<Board> getById(Long id, int userId) {
		return Mono.justOrEmpty(boardDao.getById(id, userId)).retry(3)
			.switchIfEmpty(Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!"))));
	}
}
