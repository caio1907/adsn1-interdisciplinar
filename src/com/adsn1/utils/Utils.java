package com.adsn1.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String formatDateToSql(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public static String formatDateToSql(Date date, boolean withTime ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" + (withTime ? " HH:mm:ss" : ""));
		return dateFormat.format(date);
	}

	public static String formatToDecimal(double taxa) {
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMinimumFractionDigits(2);
		return numberFormat.format(taxa/100);
	}
	
	public static Date formatStringToDate(String date) {
		if (date.contains("/")) {
			String[] dateSplitted = date.split("/");
			date = dateSplitted[2]+"-"+dateSplitted[1]+"-"+dateSplitted[0];
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formatDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	public static String formatDateToString(Date date, boolean withTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" + (withTime ? " HH:mm:ss" : ""));
		return dateFormat.format(date);
	}
}
