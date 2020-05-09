package com.nathanormond.model.data.database;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;

public class PostgreSQLDAO_Test {


	/*********************************
	 * TEST DATA FOR DEV ONLY: 
	 */

	private String testTokens[] = {"id", "first_name", "last_name"};
	private int testIterations = 10;
	private String tableName = "tests";
	
	private List<String> createTestData(){ 
		
		List<String> testData = new ArrayList<String>();
		
		for(int i = 0; i < this.testIterations; i++) { 
			testData.add(String.format("insert into %s (%s, %s, %s) values (%d, '%s', '%s')", 
													this.tableName,
													this.testTokens[0], this.testTokens[1], this.testTokens[2],
													i, this.testTokens[1] + i, this.testTokens[2] + i));
		}
		
		return testData;
	}
	
	private String createTestTable() { 
		return String.format("create table IF NOT EXISTS %s ( id INT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL)", this.tableName);
	}
	
	@Test 
	public void singleTonTests() { 
		//TODO
	}
	
	@Test
	public void tableTests() throws SQLException, InterruptedException { 
		assertTrue(createTableTests());
		Thread.sleep(500);
		assertTrue(populateTableTests());
		Thread.sleep(500);
		assertTrue(queryTableTests());
		Thread.sleep(500);
		assertTrue(deleteTableTests());
	}   
	
	public boolean createTableTests() { 
		return PostgreSQLDAOSingleton.getIntance().executeStatement(createTestTable());
	}
	
	public boolean populateTableTests() { 
		return PostgreSQLDAOSingleton.getIntance().updateDBMulti(createTestData());
	}

	public boolean queryTableTests() throws SQLException { 
		ResultSet rs = PostgreSQLDAOSingleton.getIntance().performQuery(String.format("SELECT * FROM %s", this.tableName));
		List<String> testData = createTestData();
		
		int iterations = 0;
		while(rs.next()) {
			
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getString("first_name"));
			System.out.println(rs.getString("last_name"));
			
			if(! ((rs.getInt(testTokens[0]) == iterations) && (rs.getString(testTokens[1]).equals(testTokens[1]+iterations)) && (rs.getString(testTokens[2]).equals(testTokens[2]+iterations)))) { 
				return false;
			}
			
			iterations++;
			
			System.out.println(iterations);
		}
		
		System.out.println(iterations);
		
		return true;
	}
	
	public boolean deleteTableTests() { 
		String sql = String.format("DROP TABLE IF EXISTS %s", this.tableName);
		return PostgreSQLDAOSingleton.getIntance().executeStatement(sql);
	}

}
