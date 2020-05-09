package com.nathanormond.model.data.database.postgre;

public class PostgreSQLConsts {
	
	private static final String connURL = "jdbc:postgresql://localhost:5432/local_synoptic";
	private static final String userName = "postgres";
	private static final String password = "passw0rd";
	
	public static String getConnurl() {
		return connURL;
	}
	
	public static String getUsername() {
		return userName;
	}
	
	public static String getPassword() {
		return password;
	}

}
