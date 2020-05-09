package com.nathanormond.model.data.database.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.nathanormondmodel.data.database.AbstractDAO;
public class PostgreSQLDAOSingleton extends AbstractDAO {
	
	private static PostgreSQLDAOSingleton INSTANCE = null;

	private String connURL;
	private String uname;
	private String password;

	private PostgreSQLDAOSingleton() {
		super();
		this.connURL = PostgreSQLConsts.getConnurl();
		this.uname = PostgreSQLConsts.getUsername();
		this.password = PostgreSQLConsts.getPassword();
	}
	
	public static synchronized PostgreSQLDAOSingleton getIntance() { 
		if(INSTANCE == null) { 									// lazy loading
			INSTANCE = new PostgreSQLDAOSingleton();
		}
		return INSTANCE;
	}
	
	
	/********************************************************************************************************************
	 * 
	 */
	
	
	/**
	 * 
	 */
	@Override
	public Connection openConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.connURL, this.uname, this.password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}

	/**
	 * 
	 */
	@Override
	public boolean closeConnection(Connection conn) {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public boolean executeStatement(String sql) { 
		Connection conn = openConnection();
		boolean executed = false;
		try { 
			Statement stmt = conn.createStatement();
			executed = stmt.execute(sql);
			stmt.close();
		}catch(SQLException e) { 
			e.printStackTrace();
		}
		return executed;
	}
	

	@Override
	public boolean updateDBSingle(String sql) {
		Connection conn = null;
	    Statement stmt = null;
		
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
			return false;
		}
		
		closeConnection(conn);
		return true;
	}
	
	
	/**
	 * @param sqls
	 * @return
	 */
	public boolean updateDBMulti(List<String> sqls) {
		Connection conn = null;
	    Statement stmt = null;
		
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			for(String sql : sqls) { 
				stmt.executeUpdate(sql);
			}
			
			stmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
			return false;
		}
		
		closeConnection(conn);
		return true;
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet performQuery(String query) { 
		Connection conn = openConnection();
		Statement stmt;
		ResultSet rs;

		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = getResults(stmt, query);
			closeConnection(conn);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
		}
		
		closeConnection(conn);
		return null;
	}

	
	
}
