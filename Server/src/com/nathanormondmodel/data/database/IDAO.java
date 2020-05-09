package com.nathanormondmodel.data.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IDAO {
	
	public abstract Connection openConnection();
	public abstract boolean closeConnection(Connection conn);
	public abstract boolean executeStatement(String sql);
	public abstract boolean updateDBSingle(String sql);
	public abstract boolean updateDBMulti(List<String> sqls);
	public abstract ResultSet performQuery(String query);

}
