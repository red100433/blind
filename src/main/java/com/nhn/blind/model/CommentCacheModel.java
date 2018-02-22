package com.nhn.blind.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentCacheModel {
	private long Time;
	private List<Comment> comment;
}
