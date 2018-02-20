package com.nhn.blind.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.blind.cache.BoardCache;
import com.nhn.blind.exception.UserException;
import com.nhn.blind.model.Board;
import com.nhn.blind.model.Comment;
import com.nhn.blind.repository.BoardDao;
import com.nhn.blind.repository.CommentDao;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@EnableAsync
public class BoardService {
	@Autowired
	private BoardDao boardDao;

//	@Autowired
//	private BoardCache boardCache;

	/**
	 * boardDao.getList가 3번 fail 되면 runtimeException을날림
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@Async(value = "myBoardThreadPool")
	public CompletableFuture<Flux<Board>> getList(Long next) throws InterruptedException {
//		if (next.equals(-1L) | next.compareTo(boardDao.getLastIndexBoardId()) > 0) {
//			log.info("Board Cache Data");
//			return CompletableFuture.completedFuture(Flux.fromIterable(boardCache.findBoardGroup(next)).retry(3))
//					.exceptionally(e -> {
//						throw new RuntimeException(e.getMessage());
//					});
//		} 
		if (next.equals(-1L)) {
			next = boardDao.getLastBoardId();
		}
			log.info("Board DB data");
			return CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList(next)).retry(3))
					.exceptionally(e -> {
						throw new RuntimeException(e.getMessage());
					});

	}

	@Transactional
	public Mono<Boolean> add(Board board) {
		boolean cacheFlag = true;
//		if (board.getId() == null) {
//			cacheFlag = boardCache.addBoard(board);
//		} else {
//			cacheFlag = boardCache.changeBoard(board);
//		}

		if (boardDao.add(board) & cacheFlag) {
			return Mono.just(true);
		} else {
			return Mono.defer(() -> Mono.error(new RuntimeException()));
		}
	}

	@Transactional
	public Mono<Boolean> delete(Board board) {
		if (boardDao.delete(board)) { //& boardCache.deleteBoard(board)) {
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
	@Async(value = "myCommentThreadPool")
	public Mono<Board> getById(Long id, int userId) {
		return Mono.justOrEmpty(boardDao.getById(id, userId)).retry(3)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new UserException("No Access User!!!!!"))));
	}
}
