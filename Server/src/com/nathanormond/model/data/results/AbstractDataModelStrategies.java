package com.nathanormond.model.data.results;

import com.nathanormond.model.data.results.interfaces.IContactData;
import com.nathanormond.model.data.results.interfaces.IContactVesselData;
import com.nathanormond.model.data.results.interfaces.IOwnShipData;
import com.nathanormond.model.data.results.interfaces.IUserData;
import com.nathanormond.model.data.results.interfaces.IVesselData;

public abstract class AbstractDataModelStrategies implements IContactData, IContactVesselData, IOwnShipData, IUserData, IVesselData {

	
	private IContactData contactDataStrategy;
	private IContactVesselData contactVesselDataStrategy;
	private IOwnShipData ownShipDataStrategy;
	private IUserData userDataStrategy;
	private IVesselData vesselDataStrategy;
	
	
	public AbstractDataModelStrategies(IContactData contactDataStrategy, IContactVesselData contactVesselDataStrategy, IOwnShipData ownShipDataStrategy, IUserData userDataStrategy, IVesselData vesselDatatrategy) {
		this.contactDataStrategy = contactDataStrategy;
		this.contactVesselDataStrategy = contactVesselDataStrategy;
		this.ownShipDataStrategy = ownShipDataStrategy;
		this.userDataStrategy = userDataStrategy;
		this.vesselDataStrategy = vesselDatatrategy;
	}


	public IContactData getContactDataStrategy() {
		return contactDataStrategy;
	}


	public IContactVesselData getContactVesselDataStrategy() {
		return contactVesselDataStrategy;
	}


	public IOwnShipData getOwnShipDataStrategy() {
		return ownShipDataStrategy;
	}


	public IUserData getUserDataStrategy() {
		return userDataStrategy;
	}


	public IVesselData getVesselDataStrategy() {
		return vesselDataStrategy;
	}
	
	

}
