package com.nhn.blind.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Board;
import com.nhn.blind.repository.BoardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardCache {
	@Autowired
	private BoardDao boardDao;

	private static final int CACHE_POOL_SIZE = 1000;
	private final List<Board> boardCache = new LinkedList<>();
	private final long cacheDuration = 600 * 1000L;
	private long boardCacheLoadTime;
	private long lastIndexBoardId = Long.MIN_VALUE;

	/**
	 * 10분 정도의 간격을 통해서 Cache를 갱신한다. DB와 별도로 관리한다.
	 * 
	 * @param boardGroupKey
	 * @return
	 */
	public List<Board> findBoardGroup(Long next) {
		long now = System.currentTimeMillis();

		// 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
		if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
			synchronized (boardCache) {
				if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
					List<Board> list = new ArrayList<>();
					// TODO get AllList change
					
					list.addAll(boardDao.getListAll());
					lastIndexBoardId = list.get(CACHE_POOL_SIZE - 1).getId();
					
					log.info("Board Cache Set Time:{} ms", System.currentTimeMillis() - now);
					boardCache.clear();
					boardCache.addAll(list);
					boardCacheLoadTime = now;
				}
			}
		}

		if (next.equals(-1L)) {
			return firstList();
		}
				
		OptionalInt findFirst = IntStream.range(0, boardCache.size())
				.filter(i -> next.equals(boardCache.get(i).getId())).findFirst();

		return boardCache.stream().skip(findFirst.getAsInt() + 1).limit(20).collect(Collectors.toList());

	}

	public void init() {
		boardCache.clear();
	}

	/**
	 * 삽입 할 때, List 맨 처음에 값을 넣는다.
	 * 
	 * @param board
	 */
	public boolean addBoard(Board board) {
		if (boardCache.size() == CACHE_POOL_SIZE) {
			boardCache.remove(CACHE_POOL_SIZE - 1);
		}
		boardCache.add(0, board);
		lastIndexBoardId = boardCache.get(CACHE_POOL_SIZE - 1).getId();
		return true;
	}

	/**
	 * 삭제 할 때, 특정 값 삭제
	 * 
	 * @param boardId
	 */
	public boolean deleteBoard(Board board) {
		boardCache.removeIf(s -> (s.getId().equals(board.getId())) & (s.getUserId() == board.getUserId()));
		lastIndexBoardId = boardCache.get(CACHE_POOL_SIZE - 1).getId();
		return true;
	}

	/**
	 * primary key인 id 값을 찾아서 update 함.
	 * 
	 * @param updateBoard
	 */
	public boolean changeBoard(Board updateBoard) {
		boardCache.stream().filter(s -> s.getId().equals(updateBoard.getId())).findFirst().ifPresent(s -> {
			s.setTitle(updateBoard.getTitle());
			s.setContent(updateBoard.getContent());
			s.setUserId(updateBoard.getUserId());
		});
		return true;
	}
	public List<Board> firstList() {
		return boardCache.stream().skip(0).limit(20).collect(Collectors.toList());
	}
	public Long getLastIndexBoardId() {
		return lastIndexBoardId;
	}
}
