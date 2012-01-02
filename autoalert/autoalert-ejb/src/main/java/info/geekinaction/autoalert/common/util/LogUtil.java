/**
 * 
 */
package info.geekinaction.autoalert.common.util;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
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
	 * @param source
	 * @param level
	 * @param message
	 * @param throwable
	 * @param params
	 */
	public static void log(Object source, Level level, String message, Throwable throwable, Object ... params) {
		
		// Set logger name
		String loggerName = source.getClass().getName();
		Logger logger = Logger.getLogger(loggerName);
		
		// Log sequence number
		long logSequence = System.currentTimeMillis();
		
		// Log message
		String _message = message;
		if (params != null && params.length > 0) {
			MessageFormat format = new MessageFormat(message);
			StringBuffer out = new StringBuffer();
			format.format(params, out, null);
			_message = out.toString();
		}
		
		// Create log entry
		LogRecord record = new LogRecord(level, _message);
		record.setMillis(logSequence);
		record.setSequenceNumber(logSequence);
		
		if (throwable != null) {
			record.setThrown(throwable);
		}
		
		// Log event
		logger.log(record);
	}
	
	/**
	 * 
	 * @param source
	 * @param level
	 * @param message
	 * @param params
	 */
	public static void log(Object source, Level level, String message, Object ... params) {
		log(source, level, message, null, params);
	}

}
