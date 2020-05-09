package com.nathanormond.model.data.reference_types.interfaces;

public interface IVessel {
	public String getVessel_name();
	public void setVessel_name(String vessel_name);
	public int getVessel_id();
	public void setVessel_id(int vessel_id);
	public String getVessel_type();
	public void setVessel_type(String vessel_type);
	public String getVessel_info();
	public void setVessel_info(String vessel_info);
	public boolean isNull();
}
