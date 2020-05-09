package com.nathanormondmodel.data.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * DAO - Data Accessor Object
 * @author Nathan Ormond
 */
public abstract class AbstractDAO implements IDAO {

	private static String dbConnectionString;

	public AbstractDAO() { 
		
	}
	
	public AbstractDAO(String dbConnectionString) {
		setDbConnectionString(dbConnectionString);
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException { 
		return connection.prepareStatement(query);
	}
	
	public ResultSet getResults(Statement stmt, String query) throws SQLException { 
		return stmt.executeQuery(query);
	}
	
	public static String getDbConnectionString() {
		return dbConnectionString;
	}
	
	public static void setDbConnectionString(String dbConnectionString) {
		AbstractDAO.dbConnectionString = dbConnectionString;
	}
	
}
