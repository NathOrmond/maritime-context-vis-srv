package com.nathanormond.model.data.results.strategies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nathanormond.meta.date_time.DateTimeFormat;
import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.results.interfaces.IContactVesselData;
import com.nathanormond.model.data.tables.ContactVesselsRelationalTable;
import com.nathanormond.model.data.tables.ContactsRelationalTable;

public class ContactVesselResultsStrategy extends AbstractStrategy implements IContactVesselData {

	public ContactVesselResultsStrategy() {
		super(new PostgreSQLQueryBuilder(),	new ContactVesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder()));
	}

	@Override
	public Map<Integer, List<Integer>> getContactVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON) {
		ResultSet rs = super.getTable().performQuery(getSQLQuery(dateTime, constraintJSON));
		Map<Integer, List<Integer>> results = new HashMap<Integer, List<Integer>>();
		try {
			while(rs.next()) { 
				int vesselID = rs.getInt("vessel_id");
				int contactID = rs.getInt("contact_id");
				if(! (results.get(vesselID) == null)) { 
					results.get(vesselID).add(contactID);
				} else { 
					List<Integer> contacts = new ArrayList<Integer>();
					contacts.add(contactID);
					results.put(vesselID, contacts);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	private String getSQLQuery(LocalDateTime dateTime, String jsonArgs) {
		JsonElement jelement = new JsonParser().parse(jsonArgs);
		JsonObject jsonObj = jelement.getAsJsonObject();
		JsonObject northEast = jsonObj.getAsJsonObject("_northEast");
		JsonObject southWest = jsonObj.getAsJsonObject("_southWest");
		return "SELECT * FROM contact_vessels WHERE contact_id in (SELECT contact_id FROM contacts "
				+ super.getQueryBuilder().WHERE_EQUALS("date_time", DateTimeFormat.localDateTimeToString(dateTime))
				+ super.getQueryBuilder().AND("latitude  < " + northEast.get("lat").getAsDouble())
				+ super.getQueryBuilder().AND("longitude < " + northEast.get("lng").getAsDouble())
				+ super.getQueryBuilder().AND("latitude  > " + southWest.get("lat").getAsDouble())
				+ super.getQueryBuilder().AND("longitude > " + southWest.get("lng").getAsDouble()) + ")";
	}

}
