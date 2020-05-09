package com.nathanormond.model.data.reference_types.implementations;

import com.nathanormond.model.data.reference_types.interfaces.IVessel;

public class Vessel implements IVessel{
	
	private int vessel_id;
	private String vessel_name = null;
	private String vessel_type = null;
	private String vessel_info = null;
	
	public Vessel() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getVessel_name() {
		return vessel_name;
	}
	
	@Override
	public void setVessel_name(String vessel_name) {
		this.vessel_name = vessel_name;
	}
	
	@Override
	public int getVessel_id() {
		return vessel_id;
	}
	
	@Override
	public void setVessel_id(int vessel_id) {
		this.vessel_id = vessel_id;
	}
	
	@Override
	public String getVessel_type() {
		return vessel_type;
	}
	
	@Override
	public void setVessel_type(String vessel_type) {
		this.vessel_type = vessel_type;
	}

	@Override
	public String getVessel_info() {
		return vessel_info;
	}
	
	@Override
	public void setVessel_info(String vessel_info) {
		this.vessel_info = vessel_info;
	}


	@Override
	public boolean isNull() {
		return false;
	}
	
}
