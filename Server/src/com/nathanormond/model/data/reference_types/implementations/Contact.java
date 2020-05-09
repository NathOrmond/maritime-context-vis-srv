package com.nathanormond.model.data.reference_types.implementations;

import java.time.LocalDateTime;

import com.nathanormond.model.data.reference_types.interfaces.IContact;

public class Contact implements IContact {
	
	private int contact_id;
	private LocalDateTime date_time;
	private double latitude;
	private double longitude;
	private float cog;
	private float heading;
	private int depth;
	private double knots;
	
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getContact_id() {
		return contact_id;
	}
	
	@Override
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	
	@Override
	public LocalDateTime getDate_time() {
		return date_time;
	}
	
	@Override
	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}
	
	@Override
	public double getLatitude() {
		return latitude;
	}
	
	@Override
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public double getLongitude() {
		return longitude;
	}
	
	@Override
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public float getCog() {
		return cog;
	}
	
	@Override
	public void setCog(float cog) {
		this.cog = cog;
	}
	
	@Override
	public float getHeading() {
		return heading;
	}
	
	@Override
	public void setHeading(float heading) {
		this.heading = heading;
	}
	
	@Override
	public int getDepth() {
		return depth;
	}
	
	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override
	public double getKnots() {
		return knots;
	}
	
	@Override
	public void setKnots(double knots) {
		this.knots = knots;
	}
	
	@Override
	public boolean isNull() {
		return false;
	}
	
}
