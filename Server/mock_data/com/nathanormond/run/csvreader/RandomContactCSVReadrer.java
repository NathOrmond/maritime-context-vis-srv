package com.nathanormond.run.csvreader;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.model.data.reference_types.implementations.Vessel;
import com.nathanormond.run.database.MockDataAccessorObject;
import com.nathanormond.run.factories.contact.RandomContactFactory;
import com.nathanormond.run.factories.vessel.RandomVesselFactory;

public class RandomContactCSVReadrer extends AbstractCSVReader {

	public RandomContactCSVReadrer(double[][] boundaries, String name) {
		super(new RandomContactFactory(boundaries), new RandomVesselFactory(name));
		super.setVessel(super.getVesselFactory().createMockVessel("", "", ""));
	}
	
	@Override
	public void insertVessel() {
		Vessel v = super.getVessel();
		MockDataAccessorObject.getInstance().insertVesselToDB(super.getVessel());
	}

	@Override
	public void doStuffWithLine(String line) {
		Contact contact = super.getContactFactory().createContactFromLine(line);
		Vessel v = super.getVessel();
		MockDataAccessorObject.getInstance().insertContactToDB(contact);
		MockDataAccessorObject.getInstance().insertVesselContactToDB(contact, v);
	}


}
