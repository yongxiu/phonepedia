package cn.edu.nju.software.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public static String getDateNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getDateStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return dateFormat.format(date);
	}
}
