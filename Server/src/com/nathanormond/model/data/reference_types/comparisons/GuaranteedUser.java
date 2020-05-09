package com.nathanormond.model.data.reference_types.comparisons;

import java.util.List;

import com.nathanormond.model.data.reference_types.implementations.NullUser;
import com.nathanormond.model.data.reference_types.implementations.User;
import com.nathanormond.model.data.reference_types.interfaces.IUser;

public class GuaranteedUser {
	
	/**
	 * Null Object Pattern 
	 */
	public IUser compareUserToUsers(List<User> users, String userName) {
		for(User iteration : users) { 
			if(iteration.getUsername().equals(userName)) { 
				IUser user = new User();
				user.setUsername(userName);
				return user;
			}
		}
		return new NullUser(); 
	}

}
