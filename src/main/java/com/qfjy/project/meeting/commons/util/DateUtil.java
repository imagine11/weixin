package com.qfjy.project.meeting.commons.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.qfjy.project.meeting.bean.MeetingGrab;

/**
 * 日期工具类
 */
public class DateUtil {

	
	public static String getTodayDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		
		return sdf.format(new Date());
		
	}
	
	public static String getYestdayDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		Date d = new Date(System.currentTimeMillis()-1000*60*60*24);
		return sdf.format(d);
		
	}
	public static int getCount(Date smdate,Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		
		
		
		
		

		smdate=sdf.parse(sdf.format(smdate));

		bdate=sdf.parse(sdf.format(bdate));

		Calendar cal = Calendar.getInstance();

		cal.setTime(smdate);

		long time1 = cal.getTimeInMillis();

		cal.setTime(bdate);

		long time2 = cal.getTimeInMillis();

		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));

		
	}
	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		int num = getCount(date,date);
		int num1 = getTranscend(8,7);
		double i = 7/8;
		System.out.println(num1);
	}
	
	 public static int getTranscend(int count ,int myCount){
	        return (int)((new BigDecimal((float)myCount / count).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
	    }

	
}
