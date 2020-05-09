package com.nathanormond.model.data.tables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.User;
import com.nathanormond.model.data.tables.UsersRelationalTable;
import com.nathanormond.run.mockdata.MockUsers;
import com.nathanormondmodel.data.database.AbstractDAO;

public class UsersRelationalTable_Test {


	@Test
	public void userTableTests() throws InterruptedException {
		assertTrue(testDeleteUserTable());
		assertTrue(testCreateUserTable());
		assertTrue(testInsertUserData());
		assertTrue(testPrintingDataFromTable());
	} 

	
	private boolean testDeleteUserTable() {
		UsersRelationalTable userTable = new UsersRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return userTable.deleteTable();
	}
	
	
	private boolean testCreateUserTable() {
		UsersRelationalTable userTable = new UsersRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return userTable.createTable();
	}

	
	private boolean testInsertUserData() {
		UsersRelationalTable userTable = new UsersRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		List<User> expectedUsers = MockUsers.getInstance().getUsers();
		List<String> sqls = new ArrayList<String>();
		
		for(User user : expectedUsers) { 
			sqls.add(String.format("insert into %s (%s) values ('%s') ", userTable.getTableName(), "username" , user.getUsername()));
		}
		
		return userTable.updateTableMulti(sqls);	
	}

	
	private boolean testPrintingDataFromTable() {
		UsersRelationalTable userTable = new UsersRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		ResultSet rs = userTable.performQuery(new PostgreSQLQueryBuilder().SELECT_ALL(userTable.getTableName()));
		List<User> actualUsers = new ArrayList<User>();
		List<User> expectedUsers = MockUsers.getInstance().getUsers();
		

		try {
			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUsername(rs.getString("username"));
				actualUsers.add(tempUser);
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
			assertEquals(false, true);
		}
		
		for(User expectedUser : expectedUsers) { 
			Boolean match = false;
			
			for(User actualUser: actualUsers) { 
				if(expectedUser.getUsername().equals(actualUser.getUsername())) { 
					match = true;
				}
			}
			
			if(match == false) { 
				return false;
			}
		}
		
		return true;
	}

}
