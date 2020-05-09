package com.nathanormond.run.database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.reference_types.implementations.Vessel;

public class SQLStrings {
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public static String insertContactSQL(Contact contact) { 
		return String.format("INSERT INTO contacts(date_time, latitude, longitude, cog, heading, depth, knots) values ('%s',%s,%s,%s,%s,%s,%s)",dateTimeToString(contact.getDate_time()) ,Double.toString(contact.getLatitude()) ,Double.toString(contact.getLongitude()) ,Float.toString(contact.getCog()) ,Float.toString(contact.getHeading()) ,Integer.toString(contact.getDepth()) , Double.toString(contact.getKnots()) );
	}
	
	public static String insertVesselSQL(Vessel vessel) {
		return String.format("INSERT INTO vessels(vessel_name, vessel_type, vessel_info) values ('%s', '%s', '%s')",vessel.getVessel_name(), vessel.getVessel_type(), vessel.getVessel_info());
	}
	
	public static String insertVesselContactsSQL(Contact contact, Vessel vessel) { 
		return String.format("INSERT INTO contact_vessels(contact_id, vessel_id) values ((SELECT contact_id from contacts WHERE date_time='%s' AND latitude=%s), (SELECT vessel_id from vessels WHERE vessel_name='%s'))",dateTimeToString(contact.getDate_time()) ,Double.toString(contact.getLatitude()) ,vessel.getVessel_name());
	}
	
	public static String insertOwnShipSQL(Vessel vessel) { 
		return String.format("INSERT INTO own_ship(vessel_id) values ((SELECT vessel_id from vessels WHERE vessel_name='%s'))",vessel.getVessel_name());
	}
	
	public static String dateTimeToString(LocalDateTime dateTime) { 
		return dateTime.format(SQLStrings.formatter);
	}

}
