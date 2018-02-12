package com.nhn.blind.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Role {
	private int id;
	@NonNull
	private String email;
	@NonNull
	private String role;
	
}