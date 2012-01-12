/**
 * 
 */
package info.geekinaction.autoalert.view;
import static info.geekinaction.autoalert.view.ViewConstants.MESSAGES;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * Utility class for formatting dates, number and bools.
 * 
 * @author lcsontos
 *
 */
public final class FormatUtil {

	/**
	 * 
	 */
	private FormatUtil() { }
	
	/**
	 * Formats the given number with the given formatter.
	 *  
	 * @param number Number to format.
	 * @param format Formatter.
	 * @return Formatted number.
	 */
	public static String formatNumber(Float number, NumberFormat format) {
		if (number == null) {
			return null;
		}
		return format.format(number);
	}	

	/**
	 * @see FormatUtil#formatNumber(Float, NumberFormat)
	 */
	public static String formatNumber(Float number) {
		return formatNumber(number, NumberFormat.getDecimalFormat());
	}
	
	/**
	 * Formats the given date with the given formatter.
	 *  
	 * @param number Date to format.
	 * @param format Formatter.
	 * @return Formatted date.
	 */
	public static String formatDate(Date date, DateTimeFormat format) {
		if (date == null) {
			return null;
		}
		return format.format(date);
	}
	
	/**
	 * @see FormatUtil#formatDate(Date)
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DateTimeFormat.getShortDateTimeFormat());
	}
	
	/**
	 * Formats the given bool.
	 *  
	 * @param number Bool to format.
	 * @return Formatted bool.
	 */
	public static String formatBoolean(boolean value) {
		return value ? MESSAGES.yes() : MESSAGES.no();
	}
	
}
