package com.nathanormondmodel.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.reference_types.implementations.User;
import com.nathanormond.model.data.reference_types.implementations.Vessel;
import com.nathanormond.model.data.results.AbstractDataModelStrategies;
import com.nathanormond.model.data.results.strategies.ContactResultsStrategy;
import com.nathanormond.model.data.results.strategies.ContactVesselResultsStrategy;
import com.nathanormond.model.data.results.strategies.OwnShipResultsStrategy;
import com.nathanormond.model.data.results.strategies.UserResultsStrategy;
import com.nathanormond.model.data.results.strategies.VesselResultsStrategy;

public class DataModelStrategies extends AbstractDataModelStrategies {

	public DataModelStrategies() {
		super(new ContactResultsStrategy(), new ContactVesselResultsStrategy(), new OwnShipResultsStrategy(), new UserResultsStrategy(), new VesselResultsStrategy());
	}
	
	
	/************************************************************************************************
	 * DATA ACESSOR STRATEGIES
	 ************************************************************************************************/
	
	/******
	 * Contact Result Strategies
	 */
	@Override
	public List<Contact> getContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints) {
		return super.getContactDataStrategy().getContactsAtTimeLatLng(dateTime, jsonConstraints);
	}

	@Override
	public List<Contact> getContactsAtTime(LocalDateTime dateTime) {
		return super.getContactDataStrategy().getContactsAtTime(dateTime);
	}
	
	@Override
	public List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, String jsonConstraints) {
		List<Integer> vesselIDs = new ArrayList<Integer>();
		for(Vessel vessel : getVesselsAtTime_MAPCONSTRAINED(dateTime, jsonConstraints)) { 
			vesselIDs.add(vessel.getVessel_id());
		}
		return super.getContactDataStrategy().getHistoricalContactsAtTimeLatLng(dateTime, vesselIDs);
	}
	
	@Override
	public List<List<Contact>> getHistoricalContactsAtTimeLatLng(LocalDateTime dateTime, List<Integer> vesselIds) {
		return null;
	}
	
	/******
	 * Vessel Result Strategies
	 */

	@Override
	public Map<Integer, List<Integer>> getContactVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON) {
		return super.getContactVesselDataStrategy().getContactVesselsAtTime_MAPCONSTRAINED(dateTime, constraintJSON);
	}
	
	@Override
	public List<Vessel> getVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON) {
		return super.getVesselDataStrategy().getVesselsAtTime_MAPCONSTRAINED(dateTime, constraintJSON);
	}

	/******
	 * Own Ship Result Strategies
	 */
	
	@Override
	public List<Contact> getOwnShipResults(LocalDateTime dateTime) {
		return super.getOwnShipDataStrategy().getOwnShipResults(dateTime);
	}

	/******
	 * User Result Strategies
	 */
	
	@Override
	public List<User> getUsers() {
		return super.getUserDataStrategy().getUsers();
	}


}
