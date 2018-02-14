package com.nhn.blind.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

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

	@Async
	public CompletableFuture<Flux<Board>> getList() {
		return CompletableFuture.completedFuture(Flux.fromIterable(boardDao.getList()));
	}

	public boolean add(Board board) {
		return boardDao.add(board);
	}

	public boolean update(Board board) {
		return boardDao.update(board);
	}

	public boolean delete(Board board) {
		return boardDao.delete(board);
	}

	public Mono<Board> getById(Long id, int userId) {
//		Mono<ServerResponse> error = ServerResponse.notFound().build();
//		Mono<Board> justOrEmpty = Mono.justOrEmpty(boardDao.getById(id, userId));
//		return Mono.justOrEmpty(boardDao.getById(id, userId));
		return Mono.justOrEmpty(boardDao.getById(id, userId)).switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
	}
}
