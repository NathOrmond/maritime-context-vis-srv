package com.nathanormond.run.mockdata;

import java.util.ArrayList;
import java.util.List;

import com.nathanormond.model.data.reference_types.implementations.User;

public class MockUsers {
	
	private static MockUsers INSTANCE = null;
	private static String [] unames = {"map", "mapdata", "metadata"};
	
	private static List<User> users;
	
	private MockUsers() {
		users = new ArrayList<User>();
		for(String user: unames) { 
			User temp_user = new User();
			temp_user.setUsername(user);
			users.add(temp_user);
		}
	}
	
	public static synchronized MockUsers getInstance() { 
		if(INSTANCE == null) { 
			INSTANCE = new MockUsers();
		}
		
		return INSTANCE;
	}
	
	public List<User> getUsers(){ 
		return MockUsers.users;
	}

}
