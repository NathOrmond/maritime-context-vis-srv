package com.nathanormond.model.data.results.strategies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.User;
import com.nathanormond.model.data.results.interfaces.IUserData;
import com.nathanormond.model.data.tables.UsersRelationalTable;

public class UserResultsStrategy extends AbstractStrategy implements IUserData {

	public UserResultsStrategy() {
		super(new PostgreSQLQueryBuilder(),	new UsersRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder()));

	}

	@Override
	public List<User> getUsers() {
		ResultSet rs = super.getTable().performQuery(sqlQuery());
		List<User> users = new ArrayList<User>();
		
		try {
			User user;
			while(rs.next()) { 
				user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		return users;
	}
	
	
	private String sqlQuery() { 
		return super.getQueryBuilder().SELECT_ALL(super.getTable().getTableName());
	}

}
