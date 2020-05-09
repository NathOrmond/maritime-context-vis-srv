package com.nathanormond.model.data.tables;

import java.sql.ResultSet;
import java.util.List;

public interface IRelationalTable {
	
	
	public boolean createTable();
	public boolean deleteTable();
	
	public boolean updateTable(String sql);
	public boolean updateTableMulti(List<String> sqls);
	
	public ResultSet performQuery(String sqlQuery);
	
	public String getCreateTableSQL();
	public String getTableColumn(String target);
	public String getTableName();
	public List<String> getTableColumns();
	

}
