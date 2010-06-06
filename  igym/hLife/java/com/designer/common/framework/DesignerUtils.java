package com.designer.common.framework;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DesignerUtils {
	
	private static ApplicationConfig appConfig = ApplicationConfig.getInstance();
		
	public static final String DATE_TIME = appConfig.getConfigValue("dateTimePattern");
	public static final String DATE_LONG = appConfig.getConfigValue("datePattern.long");
	public static final String DATE_SHORT = appConfig.getConfigValue("datePattern.short");
	public static final String DATE_DEFAULT = appConfig.getConfigValue("datePattern.default");	
	public static final String DATE_TINY = appConfig.getConfigValue("datePattern.tini");
	
	public static final String TIME_TINY = appConfig.getConfigValue("timePattern.tini");
	public static final String TIME_24 = appConfig.getConfigValue("timePattern.24.hour");
	
	public static Double formatDouble(Double d){
		return formatDouble(d, "##0.0#");
	}
	public static Double formatDouble(Double d, String format){
		return Double.valueOf(new DecimalFormat(format).format(d));
	}
	
	public static String formatDate(Date date){
		return formatDate(date, DATE_DEFAULT);
	}

	public static String formatDate(Date date, String pattern){
		if(date == null)
			return null;
		
		return new SimpleDateFormat(pattern).format(date); 		
	}
	
	public static Date parseDate(String strDate, String pattern){
		Date returnDate = null;
		try {
			returnDate = new SimpleDateFormat(pattern).parse(strDate);
		} catch (Exception e) {
			// TODO: Log exception
		}
		
		return returnDate;
	}
	
	public static java.sql.Date toSqlDate(Date date)
	{
		return date == null? null: new java.sql.Date(date.getTime());
	}

	public static Timestamp toTimeStamp(Date date)
	{
		return date == null? null: new Timestamp(date.getTime());
	}

}
