package com.nathanormond.model.data.reference_types.implementations;

import java.time.LocalDateTime;

import com.nathanormond.model.data.reference_types.interfaces.IContact;

public class NullContact implements IContact {

	@Override
	public int getContact_id() {
		return 0;
	}

	@Override
	public void setContact_id(int contact_id) {
		
	}

	@Override
	public LocalDateTime getDate_time() {
		return null;
	}

	@Override
	public void setDate_time(LocalDateTime date_time) {
		
	}

	@Override
	public double getLatitude() {
		return 0;
	}

	@Override
	public void setLatitude(double latitude) {
		
	}

	@Override
	public double getLongitude() {
		return 0;
	}

	@Override
	public void setLongitude(double longitude) {
		
	}

	@Override
	public float getCog() {
		return 0;
	}

	@Override
	public void setCog(float cog) {
		
	}

	@Override
	public float getHeading() {
		return 0;
	}

	@Override
	public void setHeading(float heading) {
		
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public void setDepth(int depth) {
		
	}

	@Override
	public double getKnots() {
		return 0;
	}

	@Override
	public void setKnots(double knots) {
		
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
