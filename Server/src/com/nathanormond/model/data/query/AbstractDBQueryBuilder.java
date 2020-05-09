package com.nathanormond.model.data.query;

public abstract class AbstractDBQueryBuilder implements IDBQueryBuilder {
	
		 
	public AbstractDBQueryBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public String quoteWrap(String value) { 
		return String.format("\"%s\"", value);
	}
	
	
	/**
	 * From Stack Overflow : https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	 * 
	 * @param string
	 * @param radix
	 * @return TRUE if string is an integer
	 */
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}


}
