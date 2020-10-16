package com.tripadvisor.utils;

import java.util.Date;

public class DateUtils {
	public static String getTimeStamp(){
		Date date = new Date();
		String timeStamp = date.toString().replaceAll(":| ", "_");
		return timeStamp;
	}
	
	public static String[] getTommorrowDate(){
		Date date = new Date();
		String month = String.valueOf(date.getMonth()+1);
		String day = String.valueOf(date.getDate()+1);
		return new String[]{day, month};
	}
}
