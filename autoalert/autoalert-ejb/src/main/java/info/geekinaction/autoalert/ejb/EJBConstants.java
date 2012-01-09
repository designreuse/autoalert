/**
 * 
 */
package info.geekinaction.autoalert.ejb;

/**
 * @author lcsontos
 *
 */
public interface EJBConstants {
	
	public static final String AUTOALERT_MODEL_NAME = "AutoAlertModel";
	public static final String AUTOALERT_MODEL_JNDI = "ejb/AutoAlertModel";
	
	public static final String AUTOALERT_AUDIT_QUEUE = "jms/aaAuditQueue";
	public static final String AUTOALERT_AUDIT_QUEUE_FACTORY = "jms/aaAuditQueueFactory";
	
	/**
	 * Measured time slice in minutes
	 */
	public static final int TIME_SLICE = 5;

	/**
	 * 
	 */
	public static final int TIME_SLICE_SEC = 0;

	/**
	 * 
	 */
	public static final int TIME_SLICE_MSEC = 0;

	/**
	 * Timeout interval (5min)
	 */
	public static final int TIMER_INTERVAL = 1 * 60 * 1000;
	
	/**
	 * Timer name
	 */
	public static final String TIMER_NAME = "AUTOALERT_TIMER";
	
}
