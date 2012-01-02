/**
 * 
 */
package info.geekinaction.autoalert.mail;

import info.geekinaction.autoalert.common.LoggableRuntimeException;
import info.geekinaction.autoalert.common.util.DateUtil;
import info.geekinaction.autoalert.mail.velocity.AutoAlertVelocityHelper;
import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;

import java.io.StringWriter;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


/**
 * @author lcsontos
 * 
 */
public class AlertMessage {
	
	private static final String MIME_TYPE = "text/html";

	private static final String MAIL_TEMPLATE = "info.geekinaction.autoalert/mail/velocity/mail_template.html";

	private static final String VM_DATABASE = "database";
	private static final String VM_TABLESPACES = "tablespaces";
	private static final String VM_DATAFILES = "datafiles";
	private static final String VM_CPU_USAGE = "cpu_usage";
	private static final String VM_IO_USAGE = "io_usage";

	private Database database;
	private List<Tablespace> tablespaces;
	private List<Datafile> datafiles;
	private InstanceCpuUsage cpuUsage;
	private InstanceIoUsage ioUsage;

	private StringWriter message;

	/**
	 * 
	 * @param tablespaces
	 * @param datafiles
	 * @param cpuUsage
	 * @param ioUsage
	 */
	public AlertMessage(Database _database, List<Tablespace> _tablespaces,
			List<Datafile> _datafiles, InstanceCpuUsage _cpuUsage,
			InstanceIoUsage _ioUsage) {
		this.database = _database;
		this.tablespaces = _tablespaces;
		this.datafiles = _datafiles;
		this.cpuUsage = _cpuUsage;
		this.ioUsage = _ioUsage;
		this.message = new StringWriter();
		
		// Initialize Velocity
		try {
			init();
		} catch (Exception e) {
			throw new LoggableRuntimeException(e);
		}
		
	}

	/**
	 * @return the tablespaces
	 */
	public List<Tablespace> getTablespaces() {
		return tablespaces;
	}

	/**
	 * @param tablespaces
	 *            the tablespaces to set
	 */
	public void setTablespaces(List<Tablespace> tablespaces) {
		this.tablespaces = tablespaces;
	}

	/**
	 * @return the datafiles
	 */
	public List<Datafile> getDatafiles() {
		return datafiles;
	}

	/**
	 * @param datafiles
	 *            the datafiles to set
	 */
	public void setDatafiles(List<Datafile> datafiles) {
		this.datafiles = datafiles;
	}

	/**
	 * @return the cpuUsage
	 */
	public InstanceCpuUsage getCpuUsage() {
		return cpuUsage;
	}

	/**
	 * @param cpuUsage
	 *            the cpuUsage to set
	 */
	public void setCpuUsage(InstanceCpuUsage cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	/**
	 * @return the ioUsage
	 */
	public InstanceIoUsage getIoUsage() {
		return ioUsage;
	}

	/**
	 * @param ioUsage
	 *            the ioUsage to set
	 */
	public void setIoUsage(InstanceIoUsage ioUsage) {
		this.ioUsage = ioUsage;
	}

	/**
	 * 
	 */
	private void init() throws Exception {
		// Initialize framework.
		AutoAlertVelocityHelper.initVelocity();
		VelocityContext context = new VelocityContext();

		Template t = Velocity.getTemplate(MAIL_TEMPLATE);
		context.put(VM_DATABASE, database);
		context.put(VM_TABLESPACES, tablespaces);
		context.put(VM_DATAFILES, datafiles);
		context.put(VM_CPU_USAGE, cpuUsage);
		context.put(VM_IO_USAGE, ioUsage);

		// Render
		t.merge(context, message);

	}
	
	/**
	 * 
	 */
	public static void sendMessage(Session mailSession, String _from, String[] _rcpts, String subject,
			String text) {
		
		// Sender
		Address from = createAddress(_from);

		// Recipients
		Address[] rcpts = new Address[_rcpts.length];
		for (int index = 0; index < _rcpts.length; index++ ) {
			rcpts[index] = createAddress(_rcpts[index]);
		}
		
		// Send alert message
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setSubject(subject);
			msg.setSentDate(DateUtil.currentTime());
			msg.setFrom(from);
			msg.setRecipients(Message.RecipientType.TO, rcpts);
			msg.setContent(text, MIME_TYPE);
			Transport.send(msg);
		} catch (MessagingException e) {
			throw new LoggableRuntimeException(e);
		}

	}
	
	/**
	 * 
	 * @param address
	 * @return
	 */
	private static Address createAddress(String address) {
		try {
			Address inetAddr = new InternetAddress(address);
			return inetAddr;
		} catch (AddressException e) {
			StringBuilder builder = new StringBuilder();
			builder.append("Invalid e-mail address: ");
			builder.append(address);
			builder.append('\n');
			builder.append(e.getMessage());
			throw new LoggableRuntimeException(builder.toString(), e);
		}
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return message.toString();
	}
}
