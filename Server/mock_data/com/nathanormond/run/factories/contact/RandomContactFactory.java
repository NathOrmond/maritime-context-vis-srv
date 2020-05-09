package com.nathanormond.run.factories.contact;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nathanormond.model.data.reference_types.implementations.Contact;
import com.nathanormond.run.database.SQLStrings;

public class RandomContactFactory implements IContactFactory {
	
	private double[][] bounds;
	private double[] latLng = null;
	private Double gradient = null;
	
	public RandomContactFactory(double[][] bounds) {
		this.bounds = bounds;
	}

	@Override
	public Contact createContactFromLine(String line) {
		incrementLatLng();
		Contact contact = new Contact();
		String[] tokens = line.split(",");
		contact.setDate_time(LocalDateTime.parse(tokens[0], SQLStrings.formatter));
		contact.setLatitude(new Double(this.latLng[0]));
		contact.setLongitude(new Double(this.latLng[1]));
		contact.setCog(0f);
		contact.setHeading(0f);
		contact.setDepth(0);
		contact.setKnots(0);
		return contact;
	}
	
	/*********************************************************
	 * Random Contact Generation
	 */
	
	private double[] getRandomLatLong() { 
		double[] latLng = new double[2];
		latLng[0] = getRandomInRange(this.bounds[0][0], this.bounds[1][0]);
		latLng[1] =  getRandomInRange(this.bounds[0][1], this.bounds[1][1]);
		return latLng;
	}
	
	private double getRandomInRange(double max, double min) { 
        double range = max - min; 
        return (Math.random() * range) + min; 
	}
	
	private double[] getFurtherstLatLong() { 
		Map<String, List<Double>> corners = new HashMap<String, List<Double>>();
		Map<String, Double> distances = new HashMap<String, Double>();
		//NE
		List<Double> corner = new ArrayList<Double>();
		corner.add(new Double(this.bounds[0][0]));
		corner.add(new Double(this.bounds[0][1]));
		corners.put("NE", corner);
		distances.put("NE", calculateLength(this.latLng, corner));
		//SE
		corner = new ArrayList<Double>();
		corner.add(new Double(this.bounds[0][0]));
		corner.add(new Double(this.bounds[1][1]));
		corners.put("SE", corner);
		distances.put("SE", calculateLength(this.latLng, corner));
		//SW
		corner = new ArrayList<Double>();
		corner.add(new Double(this.bounds[1][0]));
		corner.add(new Double(this.bounds[1][1]));
		corners.put("SW", corner);
		distances.put("SW", calculateLength(this.latLng, corner));
		//NW
		corner = new ArrayList<Double>();
		corner.add(new Double(this.bounds[1][0]));
		corner.add(new Double(this.bounds[0][1]));
		corners.put("NW", corner);
		distances.put("NW", calculateLength(this.latLng, corner));
		Double longest = null;
		String longestKey = "";
		Iterator iterator = distances.entrySet().iterator(); 
		while(iterator.hasNext()) { 
			Map.Entry mapElement = (Map.Entry)iterator.next(); 
            if((longest == null) || (((Double) mapElement.getValue()) > longest)) { 
            	longest = ((Double) mapElement.getValue());
            	longestKey = (String) mapElement.getKey();
            }
		}
		double[] rv = {corners.get(longestKey).get(0).doubleValue(), corners.get(longestKey).get(1).doubleValue()};
		return rv;	
	}
	
	private Double calculateLength(double[] coords1, List<Double> coords2) { 
		// d = root((x2 - x1)^2 + (y2-y1)^2)
		return new Double(Math.sqrt((((coords2.get(0).doubleValue() - coords1[0])*(coords2.get(0).doubleValue()  - coords1[0])) + ((coords2.get(1).doubleValue() - coords1[1])*(coords2.get(1).doubleValue() - coords1[1])))));
	}
	
	private void calculateGradient() { 
		// m = (y1-y2)/(x1-x2) Basic  straight line equations 
		//double d... lol
		double[] furthest = getFurtherstLatLong();
		double d = (this.latLng[1] - furthest[1])/(this.latLng[0] - furthest[0]);
		this.gradient = new Double(d);
	}
	
	private double[] nextCoords(double[] coords, Double gradient) { 
		double m = gradient.doubleValue();
		double newLat;
		double newLng;
		if(m >= 0) { 
			newLat = coords[0] + 0.0001;
		} else if (m < 0) { 
			newLat = coords[0] - 0.0001;
		} else { 
			newLat = coords[0];
		}
		// y2 = -m(x1-x2) - y1
		newLng = -1 * ((m * (coords[0] - newLat)) - coords[1]);
		double[] rv = {newLat, newLng};
		return rv;
	}
	
	private void incrementLatLng() { 
		if(latLng == null) { 
			latLng = getRandomLatLong();
		}
		if(this.gradient == null) { 
			calculateGradient();
		}
		this.latLng = nextCoords(this.latLng, this.gradient);
	}

}
