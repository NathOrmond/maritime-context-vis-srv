package com.nathanormond.model.data.query;

import java.util.List;

/**
 * This Class acts as an interface for building SQL queries from Java Strings
 * 
 * @author Nathan Ormond
 */
public class SQLQueryBuilder extends AbstractDBQueryBuilder {
	
	// **ALWAYS LEAVE SPACE AT END OF STATEMENT SO MULTIPLE STRINGS CAN BE CONCATENATED

	@Override
	public String SELECT_ALL(String table) {
		return String.format("SELECT * FROM %s ", table);
	}

	@Override
	public String WHERE_REGEX(String columnName, String value) {
		return String.format("WHERE %s LIKE %%s% ", columnName, value);
	}

	@Override
	public String WHERE_EQUALS(String columnName, String value) {
		return String.format("WHERE %s=\"%s\" ", columnName, value);
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
	
	
	/**
	 * Wraps values for insert statement into 
	 * syntactically correct and safe string
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return insert string
	 */
	public String MULTI_INSERT(String tableName, String[] columns, String[] values) { 
		String columnsString = " (";
		String valuesString = " (";
		
		String appendValue = "";
		
		for(int index = 0; index < columns.length; index++) {
			
			if(isInteger(values[index], 10)) { 
				appendValue = values[index];
			} else { 
				appendValue = quoteWrap(values[index]);
			}
			
			if(index != (columns.length -1)) { 
				columnsString += " " + columns[index] + ",";
				valuesString += " " + appendValue + ",";	
			} else { 
				columnsString += " " + columns[index] + ") ";
				valuesString += " " + appendValue + ") ";
			}
		}
		return String.format("INSERT INTO %s %s VALUES %s ", tableName, columnsString, valuesString);
	}
	
	
	@Override
	public String ORDERBY(String attribute) {
		//Not Implemented
		return null;
	}
	

	@Override
	public String MULTI_INSERT(List<String> tableName, List<String> columns, List<String> values) {
		//Not Implemented
		return null;
	}

	@Override
	public String CREATE_TABLE(String tableName) {
		//Not Implemented
		return null;
	}

	@Override
	public String IF_EXISTS(boolean flag) {
		//Not Implemented
		return null;
	}

	@Override
	public String EQUALS(String columnName, String columnValue) {
		//Not Implemented
		return null;
	}

	@Override
	public String SINGLE_INSERT(String tableName, String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DROP_TABLE(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
