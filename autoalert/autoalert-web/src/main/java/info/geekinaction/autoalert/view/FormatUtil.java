/**
 * 
 */
package info.geekinaction.autoalert.view;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * @author lcsontos
 *
 */
public final class FormatUtil {

	/**
	 * 
	 */
	private FormatUtil() { }
	
	/**
	 * 
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(Float number, NumberFormat format) {
		if (number == null) {
			return null;
		}
		return format.format(number);
	}	

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static String formatNumber(Float number) {
		return formatNumber(number, NumberFormat.getDecimalFormat());
	}
	
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, DateTimeFormat format) {
		if (date == null) {
			return null;
		}
		return format.format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DateTimeFormat.getShortDateTimeFormat());
	}
	
}
