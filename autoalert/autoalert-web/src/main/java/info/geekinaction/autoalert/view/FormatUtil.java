/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
