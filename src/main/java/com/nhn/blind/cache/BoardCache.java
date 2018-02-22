package com.nhn.blind.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Board;
import com.nhn.blind.repository.BoardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardCache implements Cache<Board> {
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private CommentCache commentCache;

	private final List<Board> boardCache = new LinkedList<>();
	private final long cacheDuration = 10 * 60 * 1000L;
	private long boardCacheLoadTime;
	private long lastIndexBoardId = Long.MIN_VALUE;

	/**
	 * 10분 정도의 간격을 통해서 Cache를 갱신한다. DB와 별도로 관리한다.
	 * 
	 * @param boardGroupKey
	 * @return
	 */

	@Override
	public List<Board> findGroup(Long next) {
		long now = System.currentTimeMillis();
		init(now);

		if (next.equals(-1L)) {
			return firstList();
		}

		int index = 0;
		for (Board board : boardCache) {
			if (board.getId().equals(next)) {
				break;
			}
			index++;
		}

		return boardCache.stream().skip(index + 1).limit(20).collect(Collectors.toList());

	}

	@Override
	public void init(long now) {
		// 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
		if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
			synchronized (boardCache) {
				if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
					List<Board> list = new ArrayList<>();
					// TODO get AllList change

					list.addAll(boardDao.getListAll());
					lastIndexBoardId = list.get(list.size() - 1).getId();

					log.info("Board Cache Set Time:{} ms", System.currentTimeMillis() - now);
					boardCache.clear();
					boardCache.addAll(list);
					boardCacheLoadTime = now;

					commentCache.init(now);
				}
			}
		}
	}

	/**
	 * 삭제 할 때, 특정 값 삭제
	 * 
	 * @param boardId
	 */
	public void deleteBoard(Board board) {
		boardCache.removeIf(s -> (s.getId().equals(board.getId())) & (s.getUserId() == board.getUserId()));
		lastIndexBoardId = boardCache.get(boardCache.size() - 1).getId();
	}

	/**
	 * primary key인 id 값을 찾아서 update 함.
	 * 
	 * @param updateBoard
	 */
	public void changeBoard() {
		boardCache.clear();
		boardCache.addAll(boardDao.getListAll());
		lastIndexBoardId = boardCache.get(boardCache.size() - 1).getId();
	}

	public List<Board> firstList() {
		return boardCache.stream().skip(0).limit(20).collect(Collectors.toList());
	}

	public Long getLastIndexBoardId() {
		return lastIndexBoardId;
	}

	public List<Board> getBoardCache() {
		return boardCache;
	}
}
