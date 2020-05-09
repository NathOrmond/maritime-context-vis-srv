package com.nathanormond.model.data.tables;

import java.util.ArrayList;
import java.util.List;

import com.nathanormond.model.data.query.AbstractDBQueryBuilder;
import com.nathanormond.model.generic.LocalFileReader;
import com.nathanormondmodel.data.database.AbstractDAO;

public class UsersRelationalTable extends AbstractRelationalTable {

	public UsersRelationalTable(AbstractDAO dao, AbstractDBQueryBuilder sqlBuilder) {
		super(dao, sqlBuilder);
	}

	@Override
	public String getCreateTableSQL() {
		return LocalFileReader.readScript(String.format("sql_scripts/%s.sql",getTableName()));
	}

	@Override
	public String getTableName() {
		return "users";
	}

	@Override
	public List<String> getTableColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("user_id");
		columns.add("username");
		return columns;
	}

}
