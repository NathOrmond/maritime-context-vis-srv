package com.nathanormond.model.data.query;

import java.util.List;

public interface IDBQueryBuilder {

	public abstract String CREATE_TABLE(String tableName);
	
	public abstract String DROP_TABLE(String tableName);
	
	public abstract String IF_EXISTS(boolean flag);
	
	public abstract String SELECT_ALL(String table);

	public abstract String WHERE_REGEX(String columnName, String value);

	public abstract String WHERE_EQUALS(String columnName, String value);
	
	public abstract String EQUALS(String columnName, String columnValue);

	public abstract String AND(String condition);
	
	public abstract String OR(String condition);

	public abstract String MAX(String tableName, String columnName);
	
	public abstract  String GET_COUNT(String tableName, String columnName, String value); 
		
	public abstract String DELETE(String tableName, String columnName, String value);
	
	public abstract String ORDERBY(String attribute);
	
	public abstract String MULTI_INSERT(String tableName, String[] columns, String[] values);
	 
	public abstract String MULTI_INSERT(List<String> tableName, List<String> columns, List<String> values);
	
	public abstract String SINGLE_INSERT(String tableName, String column, String value);
	
}
