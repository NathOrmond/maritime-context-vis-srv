package com.nathanormond.model.data.reference_types.implementations;

import com.nathanormond.model.data.reference_types.interfaces.IUser;

public class NullVessel implements IUser{

	@Override
	public int getUser_id() {
		return 0;
	}

	@Override
	public void setUser_id(int user_id) {
		
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public void setUsername(String username) {
		
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
