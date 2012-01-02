package info.geekinaction.autoalert.common;

import static info.geekinaction.autoalert.common.CommonConstants.BARE_TIMESTAMP_FORMAT;
import static info.geekinaction.autoalert.common.CommonConstants.ISO_TIMESTAMP_FORMAT;

import info.geekinaction.autoalert.common.util.DateUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * This is the descendant of <code>RuntimeException</code> which will be logged
 * if thrown for the first time. If this exception was caught and rethrown it
 * will not be logged again. Formally if a <code>LoggableRuntimeException</code>
 * can be found in call stack and it's <code>logged</code> property is set to
 * true logging won't take place.
 * 
 * @author csontosbl
 * @version $LastChangedRevision: 1133 $
 * 
 */
public class LoggableRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String LS_PROP = "line.separator";
	private static final String NEWLINE = System.getProperty(LS_PROP);
	private static final String UNKNOWN_HOST = "UNKNOWN";

	private boolean logged;
	private String logId;
	private long logSeqId;
	private Date logTime;
	private String logMessage;

	private String sourceClass;
	private String sourceMethod;
	private int sourceLine;

	private String stackTrace;

	/**
	 * Default constuctor
	 */
	public LoggableRuntimeException() {
		super();
		init();
		log();
	}

	/**
	 * 
	 * Creates a new <code>LoggableRuntimeException</code>
	 * 
	 * @param message
	 *            Error message
	 */
	public LoggableRuntimeException(String message) {
		super(message);
		init();
		log();
	}

	/**
	 * 
	 * Creates a new <code>LoggableRuntimeException</code>
	 * 
	 * @param cause
	 *            Parent exception
	 */
	public LoggableRuntimeException(Throwable cause) {
		super(cause);
		init();
		log();
	}

	/**
	 * 
	 * Creates a new <code>LoggableRuntimeException</code>
	 * 
	 * @param message
	 *            Error message
	 * @param cause
	 *            Parent exception
	 */
	public LoggableRuntimeException(String message, Throwable cause) {
		super(message, cause);
		init();
		log();
	}

	/**
	 * 
	 * Returns the message of this exception in the following format:
	 * 
	 * <code>[LOGID=<i>Exception ID</i>, MSG=<i>Error message</i>]</code>
	 * 
	 * @return Error message
	 * 
	 */
	@Override
	public String getMessage() {
		return logMessage;
	}

	/**
	 * 
	 * Returns if this exception has been previously logged.
	 * 
	 * @return the logged True if logged before.
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * 
	 * Set logged status of this instance.
	 * 
	 * @param logged
	 *            The logged flag to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * 
	 * Returns exception ID.
	 * 
	 * @return Exception ID.
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 
	 * Set exception ID.
	 * 
	 * @param logId
	 *            Exception ID.
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 * 
	 * Generates an ID for this exception instance in the following format:
	 * 
	 * <code><i>Timestamp</i>@<i>Hostname</i></code>
	 * 
	 * @return Generated exception ID.
	 */
	private String generateId() {
		// Hostname
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			hostname = UNKNOWN_HOST;
		}

		// Current timestamp
		String timestamp = DateUtil.toChar(logTime, BARE_TIMESTAMP_FORMAT);

		// Build exception ID
		StringBuilder builder = new StringBuilder();
		builder.append(timestamp);
		builder.append("@");
		builder.append(hostname.toUpperCase());
		builder.append("@");
		builder.append(sourceClass);
		builder.append("@");
		builder.append(sourceLine);

		return builder.toString();
	}

	/**
	 * Initializes this exception instance.
	 */
	private void init() {
		stackTrace = stackTrace();

		StackTraceElement src = getStackTrace()[0];
		sourceClass = src.getClassName();
		sourceMethod = src.getMethodName();
		sourceLine = src.getLineNumber();

		logSeqId = System.currentTimeMillis();
		logTime = new Date(logSeqId);
		logId = generateId();
		logMessage = message();
		logged = wasLogged();
	}

	/**
	 * Logs the exception if it was not logged formerly.
	 */
	private void log() {
		if (!logged) {

			StringBuilder builder = new StringBuilder();
			builder.append(logMessage);
			builder.append(NEWLINE);
			builder.append(stackTrace);
			builder.append(NEWLINE);

			LogRecord logRecord = new LogRecord(Level.SEVERE, builder
					.toString());
			logRecord.setSourceClassName(sourceClass);
			logRecord.setSourceMethodName(sourceMethod);
			logRecord.setSequenceNumber(logSeqId);
			logRecord.setMillis(logSeqId);

			Logger logger = Logger.getLogger(sourceClass);
			logger.log(logRecord);

			logged = true;
		}
	}

	/**
	 * 
	 * Checks if there is another <code>LoggableRuntimeException</code> in the
	 * call stack has been logged.
	 * 
	 * @return <code>false</code> is this is the first
	 *         <code>LoggableRuntimeException</code> in the call stack or
	 *         <code>true</code> if this is <i>N</i>th one and should not be
	 *         logged again.
	 * 
	 */
	private boolean wasLogged() {
		boolean result = false;

		Throwable cause = this;
		while ((cause = cause.getCause()) != null) {
			if (cause instanceof LoggableRuntimeException) {
				/*
				 * If this is a LoggableRuntimeException instance and has been
				 * logged before stop searching and return.
				 */
				LoggableRuntimeException lre = (LoggableRuntimeException) cause;
				if (result = lre.isLogged())
					break;
			}
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	private String stackTrace() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String message() {
		// Event message
		StringBuilder builder = new StringBuilder();

		builder.append(NEWLINE);

		// Event time
		builder.append("** EVENT_TIME:\t");
		builder.append(DateUtil.toChar(logTime, ISO_TIMESTAMP_FORMAT));
		builder.append(NEWLINE);

		// Event sequence nr.
		builder.append("** EVENT_SEQ:\t");
		builder.append(logSeqId);
		builder.append(NEWLINE);

		// Event ID
		builder.append("** EVENT_REF:\t");
		builder.append(logId);
		builder.append(NEWLINE);

		// Event message
		builder.append("** EVENT_MSG:\t");
		builder.append(super.getMessage());
		builder.append(NEWLINE);

		return builder.toString();

	}

}
