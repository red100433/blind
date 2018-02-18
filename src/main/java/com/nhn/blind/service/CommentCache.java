package com.nhn.blind.service;

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
	
	   public List<Comment> findCommentGroup(String commentGroupKey) {
	        // 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
	        if (commentCache.isEmpty()) {
	            synchronized (commentCache) {
	                if (commentCache.isEmpty()) {
	                    Map<Long, List<Comment>> map = new HashMap<Long, List<Comment>>();
	                    int totalSize = commentDao.getCount();
	                    int count = 20;
	                    int page = totalSize/count;
	                    
	                    if(totalSize % count > 0) {
	                    	page ++;
	                    }
	                    
	                    for(Long i = 0L; i < page; i++) {
	                    	// 아직 페이징 미구현
	                    	//map.put(i, commentDao.getList(i));
	                    }
	 
	                    commentCache.clear();
	                    commentCache.putAll(map);
	                }
	            }
	        }
	 
	        return commentCache.get(commentGroupKey);
	    }
}
