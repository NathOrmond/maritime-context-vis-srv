package com.nathanormond.run.mockdata;
import com.nathanormond.run.csvreader.AbstractCSVReader;
import com.nathanormond.run.csvreader.GenericContactCSVReader;
import com.nathanormond.run.csvreader.OwnShipCSVReader;
import com.nathanormond.run.csvreader.RandomContactCSVReadrer;
import com.nathanormond.run.database.MockDataAccessorObject;

public class MockData {
	
	AbstractCSVReader csvReader = null;
	
	public MockData() {
		System.out.println("Creating Tables ...");
		MockDataAccessorObject.getInstance().refreshTables();
		createMockCSVData();
		atlantic();
		atlantic2();
		
	}
	
	//Create test contacts out in the Atlantic Ocean!
	public void atlantic() {
		String groupName = "Atlantic1";
		System.out.println(groupName);
		double[][] boundaries = new double[2][2];
		//NE Lat Lng
		boundaries[0][0] = 59.9770;
		boundaries[0][1] = -18.8525;
		//SE Lat Lng
		boundaries[1][0] = 39.09596;
		boundaries[1][1] = -45.4833;
		createPsuedoMockContacts(50, boundaries, groupName);
	}
	
	public void atlantic2() { 
		String groupName = "Atlantic2";
		System.out.println(groupName);
		double[][] boundaries = new double[2][2];
		//NE Lat Lng
		boundaries[0][0] = 21.49396;
		boundaries[0][1] = -30.080566;
		//SE Lat Lng
		boundaries[1][0] = 5.57224;
		boundaries[1][1] = -43.395996;
		createPsuedoMockContacts(50, boundaries, groupName);
		System.out.println("Portsmouth Done");
	}
	
	public void pacific() { 
		String groupName = "Pacific1";
		System.out.println(groupName);
		double[][] boundaries = new double[2][2];
		//NE Lat Lng
		boundaries[0][0] = 46.07323;
		boundaries[0][1] = -134.387;
		//SE Lat Lng
		boundaries[1][0] = -13.58192;
		boundaries[1][1] = -187.64648;
		createPsuedoMockContacts(100, boundaries, groupName);
	}
	
	public void createMockCSVData() {
		System.out.println("Creating own ship data...");
		csvReader = new OwnShipCSVReader();
		csvReader.readCSVToDatabase("data/own_ship_mock_15mins.csv");
		System.out.println("Creating Mock Vessel 1 Data...");
		csvReader = new GenericContactCSVReader("mock-contact-1", "cargo-ship", "transporting trainers");
		csvReader.readCSVToDatabase("data/contact_1_mock_15mins.csv");
		System.out.println("Creating Mock Vessel 2 Data...");
		csvReader = new GenericContactCSVReader("mock-contact-2", "pleasure-craft", "some footballer on holiday");
		csvReader.readCSVToDatabase("data/contact_2_mock_15mins.csv");
		System.out.println("Creating Mock Vessel 3 Data...");
		csvReader = new GenericContactCSVReader("mock-contact-3", "first-rate-ship-of-the-line", "hunting for the santissima trinidad");
		csvReader.readCSVToDatabase("data/contact_3_mock_15mins.csv");
	}
	
	public void createPsuedoMockContacts(int num, double[][] boundaries, String name) {
		
		for(int index = 0; index < num; index ++) { 
			System.out.println(index);
			csvReader = new RandomContactCSVReadrer(boundaries, (name + "" +index));
			csvReader.readCSVToDatabase("data/mock_time.csv");
		}
	}
	
		
}
