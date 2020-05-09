package com.nathanormond.model.generic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LocalFileReader {

	public static String readScript(String fileName) {
		return LocalFileReader.readFile(fileName, ';');
	}
	
	
	public static String readFile(String fileName, char terminatingChar) { 
		String rv = "";

		File file;
		FileReader reader = null;

		try {
			file = new File(fileName);
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		char c;
		if (reader != null) {
			try {
				while ((c = (char) reader.read()) != terminatingChar) {
					rv += c;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rv;
	}

}
