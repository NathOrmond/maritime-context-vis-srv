package com.nathanormond.meta.date_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public static LocalDateTime stringToLocalDateTime(String str) { 
		return LocalDateTime.parse(str, DateTimeFormat.formatter);
	}
	
	public static String localDateTimeToString(LocalDateTime dateTime) { 
		return dateTime.format(formatter);
	}
	
	public static DateTimeFormatter getFormatter() { 
		return DateTimeFormat.formatter;
	}

}
