package com.tripadvisor.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getTimeStamp(){
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	public static String[] getCheckInDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);   
	    String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2);   	    
		return new String[]{date, month, year};
	}
	
	public static String[] getCheckOutDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 6);  
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);
	    String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2);   	   	    
		return new String[]{date, month, year};
	}
	
}
