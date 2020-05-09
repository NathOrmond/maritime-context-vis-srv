package com.nathanormond.model.data.tables;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.tables.ContactsRelationalTable;

public class ContactsRelationalTable_Test {

	@Test
	public void contactsTableTests() throws InterruptedException {
		assertTrue(testDeletecontactsTable());
		assertTrue(testCreatecontactsTable());
//		assertTrue(testInsertUserData());
//		assertTrue(testPrintingDataFromTable());
	} 

	private boolean testDeletecontactsTable() {
		ContactsRelationalTable contactsTable = new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return contactsTable.deleteTable();
	}
	
	
	private boolean testCreatecontactsTable() {
		ContactsRelationalTable contactsTable = new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		return contactsTable.createTable();
	}

	
	
	/***********************************
	 * TODO
	 */
	
	private boolean testInsertUserData() {
		ContactsRelationalTable contactsTable = new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		List<String> sqls = new ArrayList<String>();
		//TODO
		
		
		return contactsTable.updateTableMulti(sqls);	
	}

	
	private boolean testPrintingDataFromTable() {
		ContactsRelationalTable contactsTable = new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder());
		ResultSet rs = contactsTable.performQuery(new PostgreSQLQueryBuilder().SELECT_ALL(contactsTable.getTableName()));
		//TODO
		
		
		return false;
	}
}
