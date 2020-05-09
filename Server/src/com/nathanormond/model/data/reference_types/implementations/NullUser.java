package com.nathanormond.model.data.reference_types.implementations;

import com.nathanormond.model.data.reference_types.interfaces.IUser;

public class NullUser implements IUser {

	@Override
	public int getUser_id() {
		return -1;
	}

	@Override
	public void setUser_id(int user_id) {
		//Do nothing - immutale
	}

	@Override
	public String getUsername() {
		return "map";
	}

	@Override
	public void setUsername(String username) {
		//Do nothing - immutable
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
