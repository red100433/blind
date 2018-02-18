package com.nhn.blind.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Comment;
import com.nhn.blind.repository.CommentDao;

@Component
public class CommentCache {
	@Autowired
	private CommentDao commentDao;

	private final Map<Long, List<Comment>> commentCache = new HashMap<>();
	private final long cacheDuration = 600 * 1000L;
	private long commentCacheLoadTime;

	
	/**
	 * 만약 캐시를 거치지 않고 DB값을 변경시킬 경우 cache에 적용되지 않기 때문에 10분 정도의 간격을 통해서 Cache를 갱신한다.
	 * 캐시 데이터는 게시판마다 달려있는 댓글을 캐시한다.
	 * @param commentGroupKey
	 * @return
	 */
	public List<Comment> findCommentGroup(String commentGroupKey) {
		long now = System.currentTimeMillis();

		// 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
		if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
			synchronized (commentCache) {
				if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
					Map<Long, List<Comment>> map = new HashMap<Long, List<Comment>>();

					int groupByBoardIdCount = commentDao.getCount();
					for (Long i = 0L; i < groupByBoardIdCount; i++) {
						// 아직 페이징 미구현
						// boardId, comment Map
						// map.put(i, commentDao.getList(i));
					}

					commentCache.clear();
					commentCache.putAll(map);
					commentCacheLoadTime = now;
				}
			}
		}

		return commentCache.get(commentGroupKey);
	}
}
