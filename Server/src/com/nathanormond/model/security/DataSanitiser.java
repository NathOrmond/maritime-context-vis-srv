package com.nathanormond.model.security;

public class DataSanitiser {

	public DataSanitiser() {
		// Not used
	}
	
	public static boolean isStringSanitised(String str) { 
		
		if(!str.contains("DROP")) { 	//must satisfy condition to return true
			return true;
		}
		
		return false; 					// default to return false
	}
	
}
