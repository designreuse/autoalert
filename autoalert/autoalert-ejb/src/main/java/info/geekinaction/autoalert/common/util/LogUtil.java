/**
 * 
 */
package info.geekinaction.autoalert.common.util;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

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
	 * @param source
	 * @param level
	 * @param message
	 * @param throwable
	 * @param params
	 */
	public static void log(Object source, Level level, String message, Throwable throwable, Object ... params) {
		Logger logger = Logger.getLogger(source.getClass());
		log(logger, level, message, throwable, params);		
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
