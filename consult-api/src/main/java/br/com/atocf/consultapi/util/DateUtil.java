package br.com.atocf.consultapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final DateFormat DATE_TIME_SEC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static String formatDatetoString(Date date, DateFormat dateFormat) {
		if(date != null){
			return dateFormat.format(date);
		}
		return null;
	}
	
	public static Date parseStringtoDate(String date, DateFormat dateFormat) throws ParseException {
		if(date != null && date != ""){
			return dateFormat.parse(date);
		}
		return null;
	}
}
