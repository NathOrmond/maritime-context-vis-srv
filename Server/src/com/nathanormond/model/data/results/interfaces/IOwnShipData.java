package com.nathanormond.model.data.results.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.nathanormond.model.data.reference_types.implementations.Contact;

public interface IOwnShipData {

	List<Contact> getOwnShipResults(LocalDateTime dateTime);
	
}
