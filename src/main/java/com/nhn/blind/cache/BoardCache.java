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

/**
 * 게시판 캐시
 * @author daeyun.jang
 *
 */
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

	/**
	 * 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져옴
	 * 처음 1000개 게시판 Cache Setting 시간 20 ~ 30 ms
	 * 
	 */
	@Override
	public void init(long now) {
		// 
		if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
			synchronized (boardCache) {
				if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
					List<Board> list = new ArrayList<>();

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
	 * 게시판을 삭제했을 때, 캐시에 있는 데이터도 삭제한다.
	 * 
	 * @param boardId
	 */
	public void deleteBoard(Board board) {
		boardCache.removeIf(s -> (s.getId().equals(board.getId())) & (s.getUserId() == board.getUserId()));
		lastIndexBoardId = boardCache.get(boardCache.size() - 1).getId();
	}

	/**
	 * 게시판이 없데이트 되면 게시판을 다시 세팅한다
	 * 1000개 불러오는데 20~30ms
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
