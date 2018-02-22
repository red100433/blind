package com.nhn.blind.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhn.blind.model.Board;
import com.nhn.blind.model.Comment;
import com.nhn.blind.model.CommentCacheModel;
import com.nhn.blind.repository.CommentDao;

import lombok.extern.slf4j.Slf4j;

/**
 * 댓글 캐시
 * @author daeyun.jang
 *
 */
@Slf4j
@Component
public class CommentCache implements Cache<Comment> {
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private BoardCache boardCache;

	private final Map<Long, CommentCacheModel> commentCache = new HashMap<>();
	private final long cacheDuration = 60 * 60 * 1000L;
	private long commentCacheLoadTime;

	/**
	 * 60분 동안 접근이 없으면 다시 Cache를 갱신한다. 값의 변경이 있다면 service 단에서 changeComment Method를 먼저 실행시킨다. 
	 * 캐시 데이터는 게시판마다 달려있는 댓글을 캐시한다.
	 * 
	 * 데이터가 적재되어 있다면, 접근 시간을 다시 세팅하고 값을 리턴한다.
	 * 만약 데이터가 캐시에 적재 되어 있지 않다면, 접근 시간이 가장 늦은 데이터를 지우고 새로운 데이터를 캐시에 저장한다.
	 * 
	 * @param commentGroupKey
	 * @return
	 */

	@Override
	public List<Comment> findGroup(Long commentGroupKey) {
		long now = System.currentTimeMillis();
		init(now);

		if (commentCache.get(commentGroupKey) == null) {
			synchronized (commentCache) {
				Entry<Long, CommentCacheModel> min = null;
				for (Entry<Long, CommentCacheModel> entry : commentCache.entrySet()) {
					if (min == null || min.getValue().getTime() > entry.getValue().getTime()) {
						min = entry;
					}
				}
				commentCache.remove(min.getKey());
				commentCache.put(commentGroupKey,
					CommentCacheModel.of(now, commentDao.getBoardCommentById(commentGroupKey)));
			}
		} else {
			commentCache.get(commentGroupKey).setTime(now);
		}

		return commentCache.get(commentGroupKey).getComment();
	}

	/**
	 * 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져옴
	 * 처음 1000개 게시판 Cache Setting 시간 3초
	 * 
	 */
	@Override
	public void init(long now) {

		if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
			synchronized (commentCache) {
				if (commentCache.isEmpty() | ((now - commentCacheLoadTime) > cacheDuration)) {
					Map<Long, CommentCacheModel> map = new HashMap<Long, CommentCacheModel>();

					log.info("Comment Cache Setting.....");

					for (Board board : boardCache.getBoardCache()) {
						List<Comment> boardCommentList = commentDao.getBoardCommentById(board.getId());
						map.put(board.getId(), CommentCacheModel.of(System.currentTimeMillis(), boardCommentList));
					}

					log.info("Comment Cache Set Time:{} seconds", (System.currentTimeMillis() - now) / 1000);
					commentCache.clear();
					commentCache.putAll(map);
					commentCacheLoadTime = now;
				}
			}
		}
	}

	/**
	 * 각 게시판의 댓글의 변경사항(add, delete)가 이루어 질 때, 만약 캐시되어 있다면 DB에서 값을 가지고 와 다시 세팅한다.
	 * 
	 * @param boardId
	 */
	public void changeComment(Long commentGroupKey) {
		if (commentCache.get(commentGroupKey) != null) {
			synchronized (commentCache) {
				commentCache.remove(commentGroupKey);
				commentCache.put(commentGroupKey,
					CommentCacheModel.of(System.currentTimeMillis(), commentDao.getBoardCommentById(commentGroupKey)));
			}
		}
	}
}
