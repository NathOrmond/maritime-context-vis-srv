package com.nathanormond.model.data.results.strategies;

import com.nathanormond.model.data.query.IDBQueryBuilder;
import com.nathanormond.model.data.tables.IRelationalTable;

public class AbstractStrategy {
	
	private IDBQueryBuilder queryBuilder;
	private IRelationalTable table;
	
	public AbstractStrategy(IDBQueryBuilder queryBuilder, IRelationalTable table) {
		super();
		this.queryBuilder = queryBuilder;
		this.table = table;
	}

	public IDBQueryBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public IRelationalTable getTable() {
		return table;
	}


}
