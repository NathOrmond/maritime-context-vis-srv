package com.nathanormondmodel.data;

public class DataModelSingleton extends DataModelStrategies {
	
	private static DataModelSingleton INSTANCE = null;
	
	
	private DataModelSingleton() { 
		super();
	}
	
	/************************************************************************************************
	 * SINGLETON ACCESSOR CODE
	 ************************************************************************************************/
	
	public static synchronized DataModelSingleton getInstance() { 
		if(INSTANCE == null) { 
			INSTANCE = new DataModelSingleton();
		}
		return INSTANCE;
	}
	
}
