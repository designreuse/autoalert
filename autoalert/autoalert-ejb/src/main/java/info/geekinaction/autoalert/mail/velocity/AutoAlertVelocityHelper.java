/**
 * 
 */
package info.geekinaction.autoalert.mail.velocity;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import info.geekinaction.autoalert.common.LoggableRuntimeException;
import info.geekinaction.autoalert.common.util.LogUtil;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;


/**
 * @author lcsontos
 * 
 */
public class AutoAlertVelocityHelper implements LogChute {

	private static final String VELOCITY_CONFIG_FILE = "/info.geekinaction.autoalert/mail/velocity/velocity.properties";
	private static AutoAlertVelocityHelper me = null;
	
	/**
	 * Initialize velocity framework.
	 */
	public AutoAlertVelocityHelper() {
		
		
		try {
			// Load configuration
			InputStream is = getClass().getResourceAsStream(VELOCITY_CONFIG_FILE);
			Properties config = new Properties();
			config.load(is);
			
			// INIT
			Velocity.init(config);
		} catch (Exception e) {
			throw new LoggableRuntimeException(e);
		}
	}

	/**
	 * @see org.apache.velocity.runtime.log.LogChute#init(org.apache.velocity.runtime.RuntimeServices)
	 */
	public void init(RuntimeServices runtimeServices) throws Exception {
		// According to velocity's documentation this method should be left alone...
	}

	/**
	 * @see org.apache.velocity.runtime.log.LogChute#isLevelEnabled(int)
	 */
	public boolean isLevelEnabled(int level) {
		// INFO is the finest log level we enable.
		return level > 0;
	}

	/**
	 * Log valocity's message with java.util.logging API calls. 
	 * 
	 * @see org.apache.velocity.runtime.log.LogChute#log(int, java.lang.String)
	 */
	public void log(int level, String message) {
		log(level, message, null);
	}

	/**
	 * Log valocity's message with java.util.logging API calls.
	 * 
	 * @see org.apache.velocity.runtime.log.LogChute#log(int, java.lang.String,
	 * java.lang.Throwable)
	 */
	public void log(int level, String message, Throwable throwable) {

		// Convert velocity's log level.
		Level _level = null;
		switch (level) {
		case INFO_ID:
			_level = INFO;
			break;
		case ERROR_ID:
			_level = SEVERE;
			break;
		case WARN_ID:
			_level = WARNING;
			break;
		default:
			_level = INFO;
		}
		
		// Log event.
		LogUtil.log(this, _level, message, throwable);

	}
	
	/**
	 * Singleton initializator.
	 */
	public static void initVelocity() {
		if (me == null) {
			me = new AutoAlertVelocityHelper();
		}
		
	}

}
