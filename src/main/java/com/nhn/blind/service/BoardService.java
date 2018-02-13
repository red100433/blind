package com.nhn.blind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.blind.model.Board;
import com.nhn.blind.repository.BoardDao;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public Flux<Board> getList() {
		return Flux.fromIterable(boardDao.getList());
	}

	public List<Board> getTitleList() {
		return boardDao.getTitleList();
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
		return Mono.just(boardDao.getById(id, userId));
	}
}
