package com.nathanormond.model.data.database.sqlite;

public final class SQLiteConsts {
	
	private static final String DRIVER_STRING = "jdbc:sqlite:";
	private static final String DB_ADDR = "C:\\Dev\\Projects\\Synoptic_LocalDB\\test_run.db";
	
	
	public static String getDriverString() {
		return DRIVER_STRING;
	}
	
	public static String getDbAddr() {
		return DRIVER_STRING + DB_ADDR;
	}

}
