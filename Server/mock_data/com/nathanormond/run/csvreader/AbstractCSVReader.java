package com.nathanormond.run.csvreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nathanormond.model.data.reference_types.implementations.Vessel;
import com.nathanormond.run.factories.contact.IContactFactory;
import com.nathanormond.run.factories.vessel.IVesselFactory;

public abstract class AbstractCSVReader {
	
	private IContactFactory contactFactory;
	private IVesselFactory vesselFactory;
	private Vessel vessel;
	
	public AbstractCSVReader(IContactFactory contactFactory,IVesselFactory vesselFactory) {
		this.contactFactory = contactFactory;
		this.vesselFactory = vesselFactory;
	}
	
	public IContactFactory getContactFactory() {
		return contactFactory;
	}

	public void setContactFactory(IContactFactory contactFactory) {
		this.contactFactory = contactFactory;
	}

	public IVesselFactory getVesselFactory() {
		return vesselFactory;
	}

	public void setVesselFactory(IVesselFactory vesselFactory) {
		this.vesselFactory = vesselFactory;
	}
	
	public Vessel getVessel() { 
		return this.vessel;
	}
	
	public void setVessel(Vessel vessel) { 
		this.vessel = vessel;
	}

	public abstract void doStuffWithLine(String line);
	public abstract void insertVessel();
	
	public void readCSVToDatabase(String csvFileName) { 
		insertVessel();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFileName));
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				doStuffWithLine(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
