package info.geekinaction.autoalert.common;

/**
 * System wide constants.
 *  
 * @author csontosbl
 * @version $LastChangedRevision: 1056 $
 */
public interface CommonConstants {

	/**
	 * Prefix of all JNDI names.
	 */
	public static final String JNDI_PREFIX = "java:comp/env";
	
	/**
	 * UTF-8 character set name.
	 */
	public static final String CHARSET_UTF8 = "UTF-8";
	
	/**
	 * ISO timestamp format.
	 */
	public static final String ISO_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * ISO date format.
	 */
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Full timestamp format without any delimiters.
	 */
	public static final String BARE_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
	
	/**
	 * MBean domain name
	 */
	public static final String MBEAN_DOMAIN_NAME = "autoalert";

	
}
