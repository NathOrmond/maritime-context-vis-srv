package com.nathanormond.run.csvreader;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.run.database.MockDataAccessorObject;
import com.nathanormond.run.factories.contact.OwnShipContactFactory;
import com.nathanormond.run.factories.vessel.OwnShipVesselFactory;

public class OwnShipCSVReader extends AbstractCSVReader {
	
	public OwnShipCSVReader() {
		super(new OwnShipContactFactory(), new OwnShipVesselFactory());
		super.setVessel(super.getVesselFactory().createMockVessel("own-ship", "submarine", "super secret navy stuff..."));
	}
	
	@Override
	public void insertVessel() {
		MockDataAccessorObject.getInstance().insertVesselToDB(super.getVessel());
		MockDataAccessorObject.getInstance().insertOwnShipToDB(super.getVessel());
	}

	@Override
	public void doStuffWithLine(String line) {
		Contact contact = super.getContactFactory().createContactFromLine(line);
		MockDataAccessorObject.getInstance().insertContactToDB(contact);
		MockDataAccessorObject.getInstance().insertVesselContactToDB(contact, super.getVessel());
	}

}
