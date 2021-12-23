package com.colson.web.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    //由出生日期获得年龄
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age+1900;
    }
    public static String dateLongToString(Long msec){
    	Date date = new Date(msec);
    	return dateToStr(date);
	}

	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getShortStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}


	public static String getStringDateShort(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}



	public static Date strToDate2(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static String strToStr(String strDate) {
    	Date date = new Date(Long.valueOf(strDate));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String dateToStr(Date dateDate) {
		if (dateDate == null) return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static String dateToStr2(Date dateDate) {
		if (dateDate == null) return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static Date strToBirthday(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	public static long getS(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate.getTime();
	}

	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	public static String format(Date date, String pattern) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getDateTime() {
		String result;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		result = formatter.format(new Date());
		return result;
	}
	public static String defaultFormat(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String defaultShortFormat(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static String getIDString(Date date) {
		return format(date, "yyyyMMddHHmmss").substring(0, 14);
	}

	public static String getIDString2() {
		return format(new Date(), "yyyyMMddHHmmss").substring(0, 14);
	}

	public static Date getNextDay(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		Date newDate = cal.getTime();
		return newDate;
	}

	/**
	 * 两个日期相减,得到天数
	 *
	 * @param startdatestr
	 * @param enddatestr
	 * @return
	 * @throws ParseException
	 */
	public static long datesub(String startdatestr, String enddatestr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startdate = sdf.parse(startdatestr);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(enddatestr));
		long endtime = calendar.getTimeInMillis();

		calendar.setTime(startdate);
		long timeend = calendar.getTimeInMillis();

		long theday = (endtime - timeend) / (1000 * 60 * 60 * 24);
		return theday;
	}

	public static long hourSub(String startdatestr, String enddatestr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date startdate = sdf.parse(startdatestr);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(enddatestr));
		long endtime = calendar.getTimeInMillis();

		calendar.setTime(startdate);
		long timeend = calendar.getTimeInMillis();

		long hour = (endtime - timeend) / (1000 * 60 * 60);
		return hour;
	}

	public static boolean isHourGreaterThan(String dateStr, String targetDateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date startdate = sdf.parse(dateStr);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(targetDateStr));
		long endtime = calendar.getTimeInMillis();

		calendar.setTime(startdate);
		long timeend = calendar.getTimeInMillis();

		return timeend>endtime;
	}

	/**
	 * 两个日期相减,得到月数
	 *
	 * @param startdatestr
	 * @param enddatestr
	 * @return
	 * @throws ParseException
	 */
	public static int monthSub(String startdatestr, String enddatestr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(enddatestr));
		int endMonth = calendar.get(Calendar.MONTH)+1;

		calendar.setTime(sdf.parse(startdatestr));
		int startMonth = calendar.get(Calendar.MONTH)+1;

		return endMonth-startMonth;
	}

}
