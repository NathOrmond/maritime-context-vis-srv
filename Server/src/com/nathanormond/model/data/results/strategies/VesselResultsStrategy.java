package com.nathanormond.model.data.results.strategies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nathanormond.meta.date_time.DateTimeFormat;
import com.nathanormond.model.data.database.postgre.PostgreSQLDAOSingleton;
import com.nathanormond.model.data.query.PostgreSQLQueryBuilder;
import com.nathanormond.model.data.reference_types.implementations.Vessel;
import com.nathanormond.model.data.results.interfaces.IVesselData;
import com.nathanormond.model.data.tables.IRelationalTable;
import com.nathanormond.model.data.tables.VesselsRelationalTable;

public class VesselResultsStrategy extends AbstractStrategy implements IVesselData {

	public VesselResultsStrategy() {
		super(new PostgreSQLQueryBuilder(),	new VesselsRelationalTable(PostgreSQLDAOSingleton.getIntance(), new PostgreSQLQueryBuilder()));
	}

	@Override
	public List<Vessel> getVesselsAtTime_MAPCONSTRAINED(LocalDateTime dateTime, String constraintJSON) {
		ResultSet rs = super.getTable().performQuery(getSQLQueryVesselsAtLatLng(super.getTable(), dateTime, constraintJSON));
		List<Vessel> vessels = new ArrayList<Vessel>();
		try {
			Vessel vessel;
			while(rs.next()) { 
				vessel = new Vessel();
				vessel.setVessel_id(rs.getInt("vessel_id"));
				vessel.setVessel_info(rs.getString("vessel_info"));
				vessel.setVessel_name(rs.getString("vessel_name"));
				vessel.setVessel_type(rs.getString("vessel_type"));
				vessels.add(vessel);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return vessels;
	}

	
	private String getSQLQueryVesselsAtLatLng(IRelationalTable table, LocalDateTime dateTime, String jsonArgs) {
		JsonElement jelement = new JsonParser().parse(jsonArgs);
		JsonObject jsonObj = jelement.getAsJsonObject();
		JsonObject northEast = jsonObj.getAsJsonObject("_northEast");
		JsonObject southWest = jsonObj.getAsJsonObject("_southWest");
		String sql = "SELECT * FROM vessels WHERE vessel_id in ("
				+ "SELECT vessel_id FROM contact_vessels WHERE contact_id in (" + "SELECT contact_id FROM contacts "
				+ super.getQueryBuilder().WHERE_EQUALS("date_time", DateTimeFormat.localDateTimeToString(dateTime))
				+ super.getQueryBuilder().AND("latitude  < " + northEast.get("lat").getAsDouble())
				+ super.getQueryBuilder().AND("longitude < " + northEast.get("lng").getAsDouble())
				+ super.getQueryBuilder().AND("latitude  > " + southWest.get("lat").getAsDouble())
				+ super.getQueryBuilder().AND("longitude > " + southWest.get("lng").getAsDouble()) + "))";
		return sql;
	}
	
	


}
