package com.nathanormond.run.database;

import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.reference_types.implementations.Vessel;
import com.nathanormond.model.data.tables.AbstractRelationalTable;
import com.nathanormond.model.data.tables.ContactVesselsRelationalTable;
import com.nathanormond.model.data.tables.ContactsRelationalTable;
import com.nathanormond.model.data.tables.OwnShipRelationalTable;
import com.nathanormond.model.data.tables.VesselsRelationalTable;

public class MockDataAccessorObject {
	
	private static MockDataAccessorObject INSTANCE = null;
	
	private AbstractRelationalTable contactsTable = null, vesselsTable = null, contactVesselsTable = null, ownShipTable = null;
	private PostgreSQLQueryBuilder sqlQueryBuilder;
	
	private  MockDataAccessorObject() {
		this.sqlQueryBuilder = new PostgreSQLQueryBuilder();
		this.ownShipTable = new OwnShipRelationalTable(PostgreSQLDAOSingleton.getIntance(), this.sqlQueryBuilder);
		this.contactVesselsTable = new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), this.sqlQueryBuilder);
		this.contactsTable = new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(), this.sqlQueryBuilder);
		this.vesselsTable = new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), this.sqlQueryBuilder);
	}
	
	public static synchronized MockDataAccessorObject getInstance() { 
		if(INSTANCE == null) { 
			INSTANCE = new MockDataAccessorObject();
		}
		return INSTANCE;
	}
	
	public static synchronized void destroyInstance() { 
		INSTANCE = null;
	}
	
	/*************************************************************
	 * METHODS
	 */
	
	public void refreshTables() { 
		deleteTables();
		createTables();
	}
	
	private void deleteTables() { 
		// NOTE MUST BE DELETED IN THIS ORDER!!!!!
		this.ownShipTable.deleteTable();
		this.contactVesselsTable.deleteTable();
		this.contactsTable.deleteTable();
		this.vesselsTable.deleteTable();
	}
	
	private void createTables() { 
		// NOTE MUST BE DELETED IN THIS ORDER!!!!!
		//Create Tables if they don't already exist
		this.contactsTable.createTable();
		this.vesselsTable.createTable();
		this.contactVesselsTable.createTable();
		this.ownShipTable.createTable();
	}
	
	/*****************************************************
	 * GENERIC DB OPERATIONS FOR MOCK DATA
	 * 
	 */
	
	public boolean insertContactToDB(Contact contact) { 
		return this.contactsTable.updateTable(SQLStrings.insertContactSQL(contact));
	}
	
	public boolean insertVesselToDB(Vessel vessel) {
		return this.vesselsTable.updateTable(SQLStrings.insertVesselSQL(vessel));
	}
	
	public boolean insertVesselContactToDB(Contact contact, Vessel vessel) { 
		return this.contactVesselsTable.updateTable(SQLStrings.insertVesselContactsSQL(contact, vessel));
	}
	
	public boolean insertOwnShipToDB(Vessel vessel) { 
		return this.ownShipTable.updateTable(SQLStrings.insertOwnShipSQL(vessel));
	}
	
}
