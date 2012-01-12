/**
 * 
 */
package info.geekinaction.autoalert.mail;

/**
 * 
 * Constants of the mail sender subsystem.
 * 
 * @author lcsontos
 *
 */
public interface MailConstants {

	/**
	 * MIME type of messages.
	 */
	public static final String MIME_TYPE = "text/html";

	/**
	 * Location of mail template.
	 */
	public static final String MAIL_TEMPLATE = "/info/geekinaction/autoalert/mail/mail_template.html";
	
	/**
	 * Location of Velocity's configuration.
	 */
	public static final String VELOCITY_CONFIG_FILE = "/info/geekinaction/autoalert/mail/velocity.properties";

	// Place-holder names in the template.
	public static final String VM_DATABASE = "database";
	public static final String VM_TABLESPACES = "tablespaces";
	public static final String VM_DATAFILES = "datafiles";
	public static final String VM_CPU_USAGE = "cpu_usage";
	public static final String VM_IO_USAGE = "io_usage";
	
}
