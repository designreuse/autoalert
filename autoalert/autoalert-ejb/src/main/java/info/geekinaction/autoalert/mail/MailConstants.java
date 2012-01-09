/**
 * 
 */
package info.geekinaction.autoalert.mail;

/**
 * @author lcsontos
 *
 */
public interface MailConstants {

	public static final String MIME_TYPE = "text/html";

	public static final String MAIL_TEMPLATE = "info/geekinaction/autoalert/mail/mail_template.html";
	public static final String VELOCITY_CONFIG_FILE = "/info/geekinaction/autoalert/mail/velocity.properties";

	public static final String VM_DATABASE = "database";
	public static final String VM_TABLESPACES = "tablespaces";
	public static final String VM_DATAFILES = "datafiles";
	public static final String VM_CPU_USAGE = "cpu_usage";
	public static final String VM_IO_USAGE = "io_usage";
	
}
