package com.nathanormond.run.factories.vessel;

import com.nathanormond.model.data.reference_types.implementations.Vessel;

public class RandomVesselFactory implements IVesselFactory {
	private String name;
	
	public RandomVesselFactory(String name) {
		this.name = name;
	}

	@Override
	public Vessel createMockVessel(String name, String type, String info) {
		return randomMockVessel();
	}
	
	public Vessel randomMockVessel() { 
		Vessel vessel = new Vessel();
		vessel.setVessel_name(this.name);
		vessel.setVessel_type("boat");
		vessel.setVessel_info("randomly generated mock vessel");
		return vessel;
	}

}
