package com.nathanormond.model.data.results.strategies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nathanormond.meta.date_time.DateTimeFormat;
import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.results.interfaces.IContactData;
import com.nathanormond.model.data.tables.ContactsRelationalTable;
import com.nathanormond.model.data.tables.IRelationalTable;

public class ContactResultsStrategy extends AbstractStrategy implements IContactData{

	
	public ContactResultsStrategy() {
		super(new PostgreSQLQueryBuilder(), new ContactsRelationalTable(PostgreSQLDAOSingleton.getIntance(),new PostgreSQLQueryBuilder()));
	}
	
	
	/*******
	 * INTERFACE METHODS
	 */
	
	@Override
	public List<Contact> getContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints) {
		return getData(latLngTimeConstrainedSQLQuery(super.getTable(), dateTime, jsonConstraints));
	}

	
	@Override
	public List<Contact> getContactsAtTime(LocalDateTime dateTime) {
		return getData(timeConstrainedSQLQuery(super.getTable(), dateTime));
	}
	
	
	@Override
	public List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, List<Integer> vesselIDs) {
		List<List<Contact>> historicalContacts = new ArrayList<List<Contact>>();
		for(Integer vesselID : vesselIDs) { 
			historicalContacts.add(getData(vesselsHistoricalContactsQuery(vesselID, dateTime)));
		}
		return historicalContacts;
	}
	
	
	@Override
	public List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints) {
		// overridden in concrete instantiation
		return null;
	}
	
	/*******
	 * SQL QUERIES
	 */
	
	private String timeConstrainedSQLQuery(IRelationalTable table, LocalDateTime dateTime) {
		return super.getQueryBuilder().SELECT_ALL(table.getTableName()) + super.getQueryBuilder().WHERE_EQUALS("date_time", DateTimeFormat.localDateTimeToString(dateTime));
	}
	
	
	private String latLngTimeConstrainedSQLQuery(IRelationalTable table, LocalDateTime dateTime, String jsonArgs) {
		JsonElement jelement = new JsonParser().parse(jsonArgs);
		JsonObject jsonObj   = jelement.getAsJsonObject();
		JsonObject northEast = jsonObj.getAsJsonObject("_northEast"); 
		JsonObject southWest = jsonObj.getAsJsonObject("_southWest"); 
		
		return  timeConstrainedSQLQuery(table, dateTime) + 
				super.getQueryBuilder().AND("latitude  < " + northEast.get("lat").getAsDouble()) +
				super.getQueryBuilder().AND("longitude < " + northEast.get("lng").getAsDouble()) +
				super.getQueryBuilder().AND("latitude  > " + southWest.get("lat").getAsDouble()) +
				super.getQueryBuilder().AND("longitude > " + southWest.get("lng").getAsDouble()) + 
			    "AND contact_id NOT IN ( SELECT contact_id FROM contact_vessels WHERE vessel_id in ( SELECT vessel_id FROM own_ship))";
	}

	
	private String vesselsHistoricalContactsQuery(Integer vesselID,LocalDateTime dateTime) { 
		return String.format("SELECT * FROM contacts WHERE contact_id in ( SELECT contact_id FROM contact_vessels WHERE vessel_id = %d) AND date_time <= '%s'",vesselID ,DateTimeFormat.localDateTimeToString(dateTime));
	}
	
	/*******
	 * RESULTS METHOD
	 */
	
	private List<Contact> getData(String sql){ 
		ResultSet rs = super.getTable().performQuery(sql);
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			Contact contact;
			while(rs.next()) { 
				contact = new Contact();
				contact.setContact_id(rs.getInt("contact_id"));
				contact.setDate_time(LocalDateTime.parse(rs.getString("date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				contact.setLatitude(rs.getDouble("latitude"));
				contact.setLongitude(rs.getDouble("longitude"));
				contact.setCog(rs.getFloat("cog"));
				contact.setHeading(rs.getFloat("heading"));
				contact.setDepth(rs.getInt("depth"));
				contact.setKnots(rs.getDouble("knots"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
