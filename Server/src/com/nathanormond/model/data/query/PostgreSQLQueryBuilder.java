package com.nathanormond.model.data.query;

import java.util.List;

public class PostgreSQLQueryBuilder extends AbstractDBQueryBuilder {

	@Override
	public String SELECT_ALL(String table) {
		return String.format("SELECT * FROM %s ", table) ;
	}

	@Override
	public String WHERE_REGEX(String columnName, String value) {
		return String.format("WHERE %s LIKE %%s% ", columnName, value);
	}

	@Override
	public String WHERE_EQUALS(String columnName, String value) {
		return String.format("WHERE %s='%s' ", columnName, value);
	}

	@Override
	public String AND(String condition) {
		return String.format("AND %s ", condition);
	}

	@Override
	public String OR(String condition) {
		return String.format("OR %s ", condition);
	}

	@Override
	public String MAX(String tableName, String columnName) {
		return String.format("SELECT MAX( %s ) FROM %s ", columnName, tableName);
	}

	@Override
	public String GET_COUNT(String tableName, String columnName, String value) {
		return String.format("SELECT count(*) AS count FROM %s %s ", tableName, this.WHERE_EQUALS(columnName, value));
	}

	@Override
	public String DELETE(String tableName, String columnName, String value) {
		return String.format("DELETE FROM %s %s ", tableName, this.WHERE_EQUALS(columnName, value));
	}

	@Override
	public String ORDERBY(String attribute) {
		return String.format("ORDER BY %s ", attribute);
	}

	@Override
	public String CREATE_TABLE(String tableName) {
		return null;
	}

	@Override
	public String IF_EXISTS(boolean flag) {
		
		return flag ? "IF EXISTS" : "IF NOT EXISTS";
	}

	@Override
	public String EQUALS(String columnName, String columnValue) {
		return String.format("%s=%s ", columnName, quoteWrap(columnValue));
	}

	@Override
	public String MULTI_INSERT(String tableName, String[] columns, String[] values) {
		return null;
	}

	@Override
	public String MULTI_INSERT(List<String> tableName, List<String> columns, List<String> values) {
		return null;
	}

	@Override
	public String SINGLE_INSERT(String tableName, String column, String value) {
		return null;
	}

	@Override
	public String DROP_TABLE(String tableName) {
		return String.format("DROP TABLE %s %s ",IF_EXISTS(true) ,tableName );
	}

}
