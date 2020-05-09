package com.nathanormond.model.data.results.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.nathanormond.model.data.reference_types.implementations.Contact;

public interface IContactData {
	
	List<Contact> getContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints);
	List<Contact> getContactsAtTime(LocalDateTime dateTime);
	List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints);
	List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, List<Integer> vesselIds);
	
}
