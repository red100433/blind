package com.nhn.blind.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Board;
import com.nhn.blind.repository.BoardDao;

@Component
public class BoardCache {
	@Autowired
	private BoardDao boardDao;

	private final Map<Long, List<Board>> boardCache = new HashMap<>();
	private final long cacheDuration = 600*1000L;
	private long boardCacheLoadTime; 

	public List<Board> findBoardGroup(String boardGroupKey) {
		long now = System.currentTimeMillis();
		
		// 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
		if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
			synchronized (boardCache) {
				if (boardCache.isEmpty() | now - boardCacheLoadTime > cacheDuration) {
					Map<Long, List<Board>> map = new HashMap<Long, List<Board>>();
					int totalSize = boardDao.getCount();
					int count = 20;
					int page = totalSize / count;

					if (totalSize % count > 0) {
						page++;
					}

					for (Long i = 0L; i < page; i++) {
						map.put(i, boardDao.getList(i));
					}

					boardCache.clear();
					boardCache.putAll(map);
					boardCacheLoadTime = now;
				}
			}
		}

		return boardCache.get(boardGroupKey);
	}
	
	public void init() {
		boardCache.clear();
	}
}
