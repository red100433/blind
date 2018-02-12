package com.nhn.blind.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class User {
	private int id;
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;
	
	public User() {}
	
}
