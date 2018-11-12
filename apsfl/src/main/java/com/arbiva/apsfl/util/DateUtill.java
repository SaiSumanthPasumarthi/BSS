package com.arbiva.apsfl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtill {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	private static final SimpleDateFormat DATE_FORMAT_TIME_ZONE = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssz");
	private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static final SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat DATE_FORMAT_STRING = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_FORMAT_STRING_dd_MMM_YYYY = new SimpleDateFormat("dd-MMM-YYYY");
	private static final SimpleDateFormat Date_FORMAT_MMMMM_YYYY = new SimpleDateFormat("MMMMM yyyy");
	private static final SimpleDateFormat DATE_FORMAT_YYYYMM = new SimpleDateFormat("yyyyMM");

	public static Date getNextDateFromCurrentDate() throws ParseException {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // number of days to add
		String tomorrowDate = DATE_FORMAT.format(c.getTime());
		return stringtoDate(tomorrowDate);
	}

	public static Calendar stringtoCalender(String datestr) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DATE_FORMAT_TIME.parse(datestr));
		return cal;
	}

	public static Calendar stringtoCalenderTime(String datestr) throws ParseException {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(DATE_FORMAT_TIME.parse(datestr));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cal;
	}

	public static Date stringtoDate(String datestr) throws ParseException {
		return DATE_FORMAT.parse(datestr);
	}

	public static Date stringtoDate(String datestr, String fmt) throws ParseException {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(fmt);
		return DATE_FORMAT.parse(datestr);
	}

	public static String stringtoString(String datestr) throws ParseException {
		String value = null;
		Date d1 = DATE_FORMAT.parse(datestr);
		value = DATE_FORMAT_STRING.format(d1);
		return value;

	}

	public static String dateToString(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static String dateToString(Date date, String fmt) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(fmt);
		return DATE_FORMAT.format(date);
	}

	public static Calendar dateTOCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String calendarToString(Calendar calendar) {
		Date date = calendar.getTime();
		return DATE_FORMAT.format(date);
	}

	public static String calendarTimeToString(Calendar calendar) {
		Date date = calendar.getTime();
		return DATE_FORMAT_TIME_ZONE.format(date);
	}

	public static String getDateInYYYYMMDD(Date prodDate) {
		Date date = Calendar.getInstance().getTime();
		if (prodDate.after(date))
			return DATE_FORMAT_YYYYMMDD.format(prodDate);
		else
			return DATE_FORMAT_YYYYMMDD.format(date);
	}

	public static Date calendarToDate(Calendar calendar) {

		return calendar.getTime();
	}

	public static String millisToString(Long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return calendarToString(cal);
	}

	public static String dateToStringTimeZone(Date date) {
		return DATE_FORMAT_TIME_ZONE.format(date);
	}

	public static String dateToStringTime(Date date) {
		return DATE_FORMAT_TIME.format(date);
	}

	public static int hoursDifference(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) diff / (60 * 60 * 1000);

	}

	public static int minutesDifference(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		int MILLI_TO_HOUR = (int) diff;
		return (int) (MILLI_TO_HOUR / (60 * 1000) % 60);
	}

	public static int hoursToMins(Date date1, Date date2) {
		long minute = Math.abs((date1.getTime() - date2.getTime()) / 60000);
		return (int) minute;
	}

	public static int daysDifference(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		int MILLI_TO_HOUR = (int) diff;
		return (int) (MILLI_TO_HOUR / (24 * 60 * 60 * 1000));
	}

	public static long gettimeDifference(Calendar cal) {

		Calendar currentCal = Calendar.getInstance();
		long milliSec1 = cal.getTimeInMillis();
		long milliSec2 = currentCal.getTimeInMillis();
		long timeDifInMilliSec = milliSec2 - milliSec1;
		long timeDifInMins = timeDifInMilliSec / (60 * 1000);
		return timeDifInMins;

	}

	public static String getCurrentDateInYYYYMMDD() {
		Calendar currentCal = Calendar.getInstance();
		Date date = currentCal.getTime();
		return DATE_FORMAT_STRING.format(date);
	}

	public static String getSTRING_dd_MMM_YYYY() {
		Calendar currentCal = Calendar.getInstance();
		Date date = currentCal.getTime();
		return DATE_FORMAT_STRING_dd_MMM_YYYY.format(date);
	}

	public static Date stringToDateString_Format(String date) throws ParseException {
		return DATE_FORMAT_STRING.parse(date);
	}

	public static Object getPreviousSTRING_dd_MMM_YYYY() {
		Calendar currentCal = Calendar.getInstance();
		Date date = currentCal.getTime();
		currentCal.setTime(date);
		currentCal.add(Calendar.DAY_OF_YEAR, -1);
		return DATE_FORMAT_STRING_dd_MMM_YYYY.format(currentCal.getTime());
	}

	public static String getDateInyyyyMM(String revDate) throws ParseException {
		Date d = Date_FORMAT_MMMMM_YYYY.parse(revDate);
		return DATE_FORMAT_YYYYMM.format(d);
	}

	public static String getCurrentYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getCurrentMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MM");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getPreviousHour() {
		
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(cal.getTime()); // sets calendar time/date
	   // cal.add(Calendar.HOUR_OF_DAY, -1); 
	    
		return new SimpleDateFormat("HH").format(cal.getTime());
	}

	public static String getCurentDay() {
		return new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
	}
	
	

	public static String getPreviousDay() {
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date()); // sets calendar time/date
	    cal.add(Calendar.DATE , -1 ); // adds one hour
	    
		return new SimpleDateFormat("dd").format(cal.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(getPreviousDay());
	}
}
