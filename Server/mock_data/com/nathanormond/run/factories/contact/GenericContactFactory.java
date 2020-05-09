package com.nathanormond.run.factories.contact;

import java.time.LocalDateTime;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.run.database.SQLStrings;

public class GenericContactFactory implements IContactFactory {

	@Override
	public Contact createContactFromLine(String line) {
		String[] tokens = line.split(",");
		LocalDateTime dateTime = LocalDateTime.parse(tokens[0], SQLStrings.formatter);
		double latitude = Double.parseDouble(tokens[1]);
		double longitude = Double.parseDouble(tokens[2]);
		float cog = 0f;
		float heading = Float.parseFloat(tokens[3]);
		int depth = 0;
		double knots = 0;
		Contact contact = new Contact();
		contact.setDate_time(dateTime);
		contact.setLatitude(latitude);
		contact.setLongitude(longitude);
		contact.setCog(cog);
		contact.setHeading(heading);
		contact.setDepth(depth);
		contact.setKnots(knots);
		return contact;
	}

}
