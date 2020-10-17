package com.tripadvisor.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getTimeStamp(){
		Date date = new Date();
		String timeStamp = date.toString().replaceAll(":| ", "_");
		return timeStamp;
	}
	
	public static String[] getCheckInDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);   
		return new String[]{date, month};
	}
	
	public static String[] getCheckOutDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 6);  
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		return new String[]{date, month};
	}
	
}
