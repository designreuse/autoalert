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
package info.geekinaction.autoalert.common.util;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 * 
 * Utiliy class for the Log4J API
 * 
 * @author lcsontos
 *
 */
public class LogUtil {

	/**
	 * 
	 */
	private LogUtil() {	}

	/**
	 * 
	 * Logs an aritrary message.
	 * 
	 * @param logger A logger by means of which this event should be logged.
	 * @param level Log level
	 * @param message Message text (which can have placeholders).
	 * @param throwable The corresponding Throwable object if the caller wants to indicate an error condition.
	 * @param params Message parameters, should be error message contain placeholders.
	 * 
	 */
	
	public static void log(Logger logger, Level level, String message, Throwable throwable, Object ... params) {
		
		// Log message
		String _message = message;
		if (params != null && params.length > 0) {
			MessageFormat format = new MessageFormat(message);
			StringBuffer out = new StringBuffer();
			format.format(params, out, null);
			_message = out.toString();
		}
		
		// Create log entry
		logger.log(level, _message, throwable);
	}
	
	/**
	 * 
	 * Logs an aritrary message.
	 * 
	 * @param source Source of the event.
	 * @param level Log level
	 * @param message Message text (which can have placeholders).
	 * @param throwable The corresponding Throwable object if the caller wants to indicate an error condition.
	 * @param params Message parameters, should be error message contain placeholders.
	 * 
	 */
	public static void log(Object source, Level level, String message, Throwable throwable, Object ... params) {
		// Create a logger for the given source object.
		Logger logger = Logger.getLogger(source.getClass());
		log(logger, level, message, throwable, params);		
	}
	
	/**
	 * Logs an aritrary message.
	 * 
	 * @param source Source of the event.
	 * @param level Log level
	 * @param message Message text (which can have placeholders).
	 * @param params Message parameters, should be error message contain placeholders.
	 * 
	 */
	public static void log(Object source, Level level, String message, Object ... params) {
		log(source, level, message, null, params);
	}

}
