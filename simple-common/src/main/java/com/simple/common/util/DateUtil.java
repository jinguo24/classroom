package com.simple.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String date2String(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String date2StringWhitNoSpilt(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}
	
	public static Date stringToDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String date2AllString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static Date allStringToDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getXueNian() {
		Calendar a=Calendar.getInstance();
		Calendar start = getXueNianStart();
		Calendar end = getXueNianEnd();
		if (a.after(start) && a.before(end)) {
			return start.get(Calendar.YEAR);
		}else if (a.after(end)) {
			return end.get(Calendar.YEAR);
		}else if (a.before(start)) {
			return a.get(Calendar.YEAR)-1;
		}
		return a.get(Calendar.YEAR);
	}
	
	private static Calendar getXueNianStart() {
		//学年开始时间是9-1，比较当前时间的月份如果小于9，则属于上一个学年
		Calendar a=Calendar.getInstance();
		a.set(Calendar.MONTH, 8);
		a.set(Calendar.DAY_OF_MONTH, 1);
		a.set(Calendar.HOUR_OF_DAY, 0);
		a.set(Calendar.MINUTE, 0);
		a.set(Calendar.SECOND, 0);
		return a;
	}
	
	private static Calendar getXueNianEnd() {
		Calendar a=Calendar.getInstance();
		a.set(Calendar.YEAR, a.get(Calendar.YEAR)+1);
		a.set(Calendar.MONTH, 8);
		a.set(Calendar.DAY_OF_MONTH, 1);
		a.set(Calendar.HOUR_OF_DAY, 0);
		a.set(Calendar.MINUTE, 0);
		a.set(Calendar.SECOND, 0);
		return a;
	}
	
	public static Date getDateStart(Date date) {
		Calendar a=Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.HOUR_OF_DAY, 0);
		a.set(Calendar.MINUTE, 0);
		a.set(Calendar.SECOND, 0);
		return a.getTime();
	}
	
	public static Date getDateEnd(Date date) {
		Calendar a=Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.HOUR_OF_DAY, 23);
		a.set(Calendar.MINUTE, 59);
		a.set(Calendar.SECOND, 59);
		return a.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.date2StringWhitNoSpilt(new Date()));
	}
}
