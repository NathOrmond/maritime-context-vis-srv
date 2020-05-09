package com.nathanormond.model.data.results.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.nathanormond.model.data.reference_types.implementations.Vessel;

public interface IVesselData {
	
	List<Vessel> getVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON);

}
