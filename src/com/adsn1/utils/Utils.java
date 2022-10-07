package com.adsn1.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String formatDateToSql(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public static String formatDateToSql(Date date, boolean withTime ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
}
