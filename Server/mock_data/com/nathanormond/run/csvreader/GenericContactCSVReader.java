package com.nathanormond.run.csvreader;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.run.database.MockDataAccessorObject;
import com.nathanormond.run.factories.contact.GenericContactFactory;
import com.nathanormond.run.factories.vessel.GenericVesselFactory;

public class GenericContactCSVReader extends AbstractCSVReader{

	public GenericContactCSVReader(String vesselName,String vesselType, String vesselInfo) {
		super(new GenericContactFactory(), new GenericVesselFactory());
		super.setVessel(super.getVesselFactory().createMockVessel(vesselName, vesselType, vesselInfo));
	}
	
	@Override
	public void insertVessel() {
		MockDataAccessorObject.getInstance().insertVesselToDB(super.getVessel());
	}

	@Override
	public void doStuffWithLine(String line) {
		Contact contact = super.getContactFactory().createContactFromLine(line);
		MockDataAccessorObject.getInstance().insertContactToDB(contact);
		MockDataAccessorObject.getInstance().insertVesselContactToDB(contact, super.getVessel());
	}

}
