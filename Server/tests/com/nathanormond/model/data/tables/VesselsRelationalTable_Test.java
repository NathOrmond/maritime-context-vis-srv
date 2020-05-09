package com.nathanormond.model.data.tables;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.tables.VesselsRelationalTable;
import com.nathanormondmodel.data.database.AbstractDAO;

public class VesselsRelationalTable_Test {

	@Test
	public void vesselsTableTests() throws InterruptedException {
		assertTrue(testDeletevesselsTable());
		assertTrue(testCreatevesselsTable());
//		assertTrue(testInsertUserData());
//		assertTrue(testPrintingDataFromTable());
	} 

	
	private boolean testDeletevesselsTable() {
		VesselsRelationalTable vesselsTable = new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return vesselsTable.deleteTable();
	}
	
	
	private boolean testCreatevesselsTable() {
		VesselsRelationalTable vesselsTable = new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return vesselsTable.createTable();
	}

	
	
	
	/***********************************
	 * TODO
	 */
	
	private boolean testInsertUserData() {
		VesselsRelationalTable vesselsTable = new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		List<String> sqls = new ArrayList<String>();
		//TODO
	
		return vesselsTable.updateTableMulti(sqls);	
	}

	
	private boolean testPrintingDataFromTable() {
		VesselsRelationalTable vesselsTable = new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		ResultSet rs = vesselsTable.performQuery(new PostgreSQLQueryBuilder().SELECT_ALL(vesselsTable.getTableName()));
		//TODO
		
		return false;
	}

}
