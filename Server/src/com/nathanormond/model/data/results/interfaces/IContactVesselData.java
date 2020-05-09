package com.nathanormond.model.data.results.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IContactVesselData {
	
	Map<Integer, List<Integer>> getContactVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON);

}
