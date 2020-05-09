package com.nathanormond.run.factories.vessel;

import com.nathanormond.model.data.reference_types.implementations.Vessel;

public class GenericVesselFactory implements IVesselFactory {

	@Override
	public Vessel createMockVessel(String name, String type, String info) {
		Vessel mockVessel = new Vessel();
		mockVessel.setVessel_name(name);
		mockVessel.setVessel_type(type);
		mockVessel.setVessel_info(info);
		return mockVessel;
	}

}
