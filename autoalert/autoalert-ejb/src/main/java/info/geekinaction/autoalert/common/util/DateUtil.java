package info.geekinaction.autoalert.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * Utility class with static helper methods for
 * manipulating date/time data.
 * 
 * @author csontosbl
 * @version $LastChangedRevision: 1056 $
 */
public final class DateUtil {

	/**
	 * Default constructor made private.
	 */
	private DateUtil() { }
	
	/**
	 * 
	 * Returns a date value used to set for <code>validTo</code> property of objects
	 * representing time series data.
	 * 
	 * @return Returns 9999-12-31 (31th of December, 9999).
	 */
	public static Date endOfValidity() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 9999);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * Returns the current time.
	 * @return The current time.
	 */
	public static Date currentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/**
	 * Returns the current date at midnight (hours, minutes and seconds are omitted).
	 * @return The current date.
	 */
	public static Date currentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * Converts the given <code>date</code> according to the supplied
	 * <code>format</code> and returns it's string representation.
	 * 
	 * @param date A date.
	 * @param format A date format.
	 * @return Formatted date.
	 */
	public static String toChar(Date date, String format) {
		if (date == null || format == null)
			throw new IllegalArgumentException("Neither date, not format can be null!");
		
		DateFormat timeStampFormat = new SimpleDateFormat(format);
		return timeStampFormat.format(date);
	}
	
	/**
	 * 
	 * Parses the given <code>date</code> according to <code>format</code> and returns
	 * it as java.util.Date instance.
	 * 
	 * @param date String representation of a date. 
	 * @param format A date format.
	 * @return Parsed date instance.
	 */
	public static Date toDate(String date, String format) throws ParseException {
		if (date == null || format == null)
			throw new IllegalArgumentException("Neither date, not format can be null!");
		
		DateFormat timeStampFormat = new SimpleDateFormat(format);
		Date result = timeStampFormat.parse(date);
		
		return result;
	}


}
