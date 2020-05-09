package com.nathanormond.model.data.tables;

import java.util.ArrayList;
import java.util.List;

import com.nathanormond.model.data.query.AbstractDBQueryBuilder;
import com.nathanormond.model.generic.LocalFileReader;
import com.nathanormondmodel.data.database.AbstractDAO;

public class ContactsRelationalTable extends AbstractRelationalTable {

	public ContactsRelationalTable(AbstractDAO dao, AbstractDBQueryBuilder sqlBuilder) {
		super(dao, sqlBuilder);
	}

	@Override
	public String getCreateTableSQL() {
		return LocalFileReader.readScript(String.format("sql_scripts/%s.sql",getTableName()));
	}

	@Override
	public String getTableName() {
		return "contacts";
	}

	@Override
	public List<String> getTableColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("contact_id");
		columns.add("date_time");
		columns.add("latitude");
		columns.add("longitude");
		columns.add("cog");
		columns.add("heading");
		columns.add("depth");
		columns.add("knots");
		return columns;
	}

}
