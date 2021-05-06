package com.tripadvisor.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/************** Get time stamp in format: yyyy.MM.dd.HH.mm.ss ****************/
	public static String getTimeStamp(){
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	/************** Get tomorrow's date ****************/
	public static String[] getCheckInDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    if(date.length()==1){
	    	date = "0"+date;
	    }//1-10-2020 //01-10-2020
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);   
	    if(month.length()==1){
	    	month = "0"+month;
	    }
	    String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2);   	    
		return new String[]{date, month, year};
	}
	
	/************** Get date five days after tomorrow's date ****************/
	public static String[] getCheckOutDate(){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 6);  
	    String date = String.valueOf(cal.get(Calendar.DATE));
	    if(date.length()==1){
	    	date = "0"+date;
	    }
	    String month = String.valueOf(cal.get(Calendar.MONTH)+1);
	    if(month.length()==1){
	    	month = "0"+month;
	    }
	    String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2);   	   	    
		return new String[]{date, month, year};
	}
	
}
