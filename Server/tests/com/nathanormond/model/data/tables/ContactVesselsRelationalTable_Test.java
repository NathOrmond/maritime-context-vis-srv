package com.nathanormond.model.data.tables;


import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.tables.ContactVesselsRelationalTable;

public class ContactVesselsRelationalTable_Test {

	@Test
	public void contactVesselsTableTests() throws InterruptedException {
		assertTrue(testDeletecontactVesselsTable());
		assertTrue(testCreatecontactVesselsTable());
//		assertTrue(testInsertUserData());
//		assertTrue(testPrintingDataFromTable());
	} 

	
	private boolean testDeletecontactVesselsTable() {
		ContactVesselsRelationalTable contactVesselsTable = new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return contactVesselsTable.deleteTable();
	}
	
	
	private boolean testCreatecontactVesselsTable() {
		ContactVesselsRelationalTable contactVesselsTable = new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return contactVesselsTable.createTable();
	}
	
	
	
	
	/***********************************
	 * TODO
	 */

	
	private boolean testInsertUserData() {
		ContactVesselsRelationalTable contactVesselsTable = new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		List<String> sqls = new ArrayList<String>();
		//TODO
		
		
		return contactVesselsTable.updateTableMulti(sqls);	
	}

	
	private boolean testPrintingDataFromTable() {
		ContactVesselsRelationalTable contactVesselsTable = new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		ResultSet rs = contactVesselsTable.performQuery(new PostgreSQLQueryBuilder().SELECT_ALL(contactVesselsTable.getTableName()));
		//TODO		
		return true;
	}
}
