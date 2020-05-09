package com.nathanormond.model.data.reference_types.interfaces;

import java.time.LocalDateTime;

public interface IContact {
	public int getContact_id();
	public void setContact_id(int contact_id);
	public LocalDateTime getDate_time();
	public void setDate_time(LocalDateTime date_time);
	public double getLatitude();
	public void setLatitude(double latitude);
	public double getLongitude();
	public void setLongitude(double longitude);
	public float getCog();
	public void setCog(float cog);
	public float getHeading();
	public void setHeading(float heading);
	public int getDepth();
	public void setDepth(int depth);
	public double getKnots();
	public void setKnots(double knots);
	public boolean isNull();
}
