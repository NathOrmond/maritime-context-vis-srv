package com.nathanormond.model.data.results.strategies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.nathanormond.meta.date_time.DateTimeFormat;
import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.results.interfaces.IOwnShipData;
import com.nathanormond.model.data.tables.OwnShipRelationalTable;

public class OwnShipResultsStrategy extends AbstractStrategy implements IOwnShipData {

	public OwnShipResultsStrategy() {
		super(new PostgreSQLQueryBuilder(),	new OwnShipRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder()));
	}

	
	@Override
	public List<Contact> getOwnShipResults(LocalDateTime dateTime) {
		ResultSet rs = super.getTable().performQuery(getSQLQuery(dateTime));

		List<Contact> contacts = new ArrayList<Contact>();

		try {
			Contact contact;
			while (rs.next()) {
				contact = new Contact();
				contact.setContact_id(rs.getInt("contact_id"));
				contact.setDate_time(LocalDateTime.parse(rs.getString("date_time"),	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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

	
	private String getSQLQuery(LocalDateTime dateTime) {
		return String.format("SELECT * FROM contacts WHERE contact_id in ( SELECT contact_id FROM contact_vessels WHERE vessel_id in ( SELECT vessel_id FROM own_ship)) AND date_time <= '%s'", DateTimeFormat.localDateTimeToString(dateTime));
	}
}
