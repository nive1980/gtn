package com.gtn.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String format(Date date, String format){
		DateFormat df = new SimpleDateFormat(format);
		String dte = df.format(date);
		return dte;
	}
	
}
