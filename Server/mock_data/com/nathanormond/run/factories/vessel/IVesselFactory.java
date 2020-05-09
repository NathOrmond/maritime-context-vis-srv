package com.nathanormond.run.factories.vessel;

import com.nathanormond.model.data.reference_types.implementations.Vessel;

public interface IVesselFactory {
	Vessel createMockVessel(String name, String type, String info);
}
