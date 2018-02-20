package com.nhn.blind.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Comment;
import com.nhn.blind.model.CommentCacheModel;
import com.nhn.blind.repository.CommentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommentCache {
	@Autowired
	private CommentDao commentDao;

	private static final int CACHE_POOL_SIZE = 500;
	private final Map<Long, CommentCacheModel> commentCache = new HashMap<>();
	private final long cacheDuration = 600 * 1000L;
	private long commentCacheLoadTime;

	/**
	 * 10분 정도의 간격을 통해서 Cache를 갱신한다. 값의 변경이 있다면 service 단에서 changeComment Method를 먼저 실행시킨다. 
	 * 캐시 데이터는 게시판마다 달려있는 댓글을 캐시한다.
	 * 
	 * @param commentGroupKey
	 * @return
	 */
	public List<Comment> findCommentGroup(Long commentGroupKey) {
		long now = System.currentTimeMillis();

		// 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
		if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
			synchronized (commentCache) {
				if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
					Map<Long, CommentCacheModel> map = new HashMap<Long, CommentCacheModel>();

					log.info("Comment Cache Setting.....");
					Long groupByBoardId = commentDao.getLastBoardId();
					
					//가장 최근에 쓰여진 글의 id 값을 가져오고 id를 1개씩 줄여나가면서 캐시에 저장한다. 만약 데이터가 없다면 다음 데이터로 넘어간다.
					for (Long i = groupByBoardId; i > 0 & map.size() <= CACHE_POOL_SIZE; i--) {
						List<Comment> boardCommentList = commentDao.getBoardCommentById(i);
						
						if (boardCommentList.size() == 0) {
							continue;
						} else {
							map.put(i, CommentCacheModel.of(1, boardCommentList));
						}
					}
					
					log.info("Comment Cache Set Time:{} seconds", (System.currentTimeMillis() - now)/1000);
					commentCache.clear();
					commentCache.putAll(map);
					commentCacheLoadTime = now;
				}
			}
		}

		// 데이터가 적재되어 있다면, 빈도수를 1개 올려주고 값을 리턴한다.
		// 만약 데이터가 캐시에 적재 되어 있지 않다면, 빈도수가 가장 낮은 데이터를 지우고 새로운 데이터를 캐시에 저장한다.
		if (commentCache.get(commentGroupKey) == null) {
			Entry<Long, CommentCacheModel> min = null;
			for (Entry<Long, CommentCacheModel> entry : commentCache.entrySet()) {
				if (min == null || min.getValue().getCount() > entry.getValue().getCount()) {
					min = entry;
				}
			}
			commentCache.remove(min.getKey());
			commentCache.put(commentGroupKey, CommentCacheModel.of(1, commentDao.getBoardCommentById(commentGroupKey)));
		} else {
			commentCache.get(commentGroupKey).setCount(commentCache.get(commentGroupKey).getCount() + 1);
		}
		
		return commentCache.get(commentGroupKey).getComment();
	}

	public void init() {
		commentCache.clear();
	}

	/**
	 * 각 게시판의 댓글의 변경사항( add, delete)가 이루어 질 때, 만약 캐시되어 있다면 빈도수는 그대로 두고 DB에서 값을 가지고 와 다시 세팅한다.
	 * 
	 * @param boardId
	 */
	public void changeComment(Long commentGroupKey) {
		if (commentCache.get(commentGroupKey) != null) {
			synchronized (commentCache) {
				int count = commentCache.get(commentGroupKey).getCount();
				commentCache.remove(commentGroupKey);
				commentCache.put(commentGroupKey,
						CommentCacheModel.of(count, commentDao.getBoardCommentById(commentGroupKey)));
			}
		}
	}
}
