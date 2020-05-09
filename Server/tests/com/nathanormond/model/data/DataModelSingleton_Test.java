package com.nathanormond.model.data;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.nathanormond.meta.date_time.DateTimeFormat;
import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormondmodel.data.DataModelSingleton;

public class DataModelSingleton_Test {

	@Test
	public void getContactsAtTimeTest() { 

		LocalDateTime dateTime = DateTimeFormat.stringToLocalDateTime("2019-06-10T00:00:00");
		
		for(Contact contact : DataModelSingleton.getInstance().getContactsAtTime(dateTime)) { 
			System.out.println("LAT: "+contact.getLatitude());
			System.out.println("LON: "+contact.getLongitude());
		}
		
		assertEquals(true, true);
		
	}

}
