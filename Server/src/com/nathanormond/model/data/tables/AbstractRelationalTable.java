package com.nathanormond.model.data.tables;

import java.sql.ResultSet;
import java.util.List;

import com.nathanormond.model.data.query.AbstractDBQueryBuilder;
import com.nathanormondmodel.data.database.AbstractDAO;

public abstract class AbstractRelationalTable implements IRelationalTable {
	
	private AbstractDAO dao = null;
	private AbstractDBQueryBuilder sqlBuilder = null;
	
	
	public AbstractRelationalTable(AbstractDAO dao, AbstractDBQueryBuilder sqlBuilder) {
		this.dao = dao;
		this.sqlBuilder = sqlBuilder;
	}
	
	
	public boolean createTable() { 
		return this.dao.executeStatement(getCreateTableSQL());
	}
	
	
	public boolean deleteTable() { 
		return this.dao.executeStatement(sqlBuilder.DROP_TABLE(getTableName()));
	}
	
	
	public boolean updateTable(String sql) { 
		return this.dao.updateDBSingle(sql);
	}
	
	
	public boolean updateTableMulti(List<String> sqls) { 
		return this.dao.updateDBMulti(sqls);
	}
	
	
	public ResultSet performQuery(String sqlQuery) { 
		return this.dao.performQuery(sqlQuery);
	}
	
	
	public String getTableColumn(String target) { 
		for(String rv : getTableColumns()){ 
			if(rv.equals(target)) { 
				return rv;
			}
		}
		return null;
	}
		
	
}
