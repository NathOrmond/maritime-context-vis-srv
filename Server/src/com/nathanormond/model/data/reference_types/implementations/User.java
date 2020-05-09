package com.nathanormond.model.data.reference_types.implementations;

import com.nathanormond.model.data.reference_types.interfaces.IUser;

public class User implements IUser {
	
	private int user_id;
	private String username = null;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getUser_id() {
		return user_id;
	}
	
	@Override
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isNull() {
		return false;
	}

}
