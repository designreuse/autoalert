/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 *
 */
public class MBeanSupport implements MBeanRegistration {
	
	private static final Logger logger = Logger.getLogger(MBeanSupport.class);
	
	protected enum RegistrationStatus {
		STARTED, FINISHED, FAILED;
	}
	
	protected ObjectName name;
	protected MBeanServer server;

	/**
	 * @see javax.management.MBeanRegistration#preRegister(javax.management.MBeanServer, javax.management.ObjectName)
	 */
	@Override
	public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
		logger.info(buildMessage(name, true, RegistrationStatus.STARTED));
		this.name = name;
		return name;
	}

	/**
	 * @see javax.management.MBeanRegistration#postRegister(java.lang.Boolean)
	 */
	@Override
	public void postRegister(Boolean registrationDone) {
		logger.info(buildMessage(name, true, registrationDone ? RegistrationStatus.FINISHED : RegistrationStatus.FAILED));
	}

	/**
	 * @see javax.management.MBeanRegistration#preDeregister()
	 */
	@Override
	public void preDeregister() throws Exception {
		logger.info(buildMessage(name, false, RegistrationStatus.STARTED));
	}

	/**
	 * @see javax.management.MBeanRegistration#postDeregister()
	 */
	@Override
	public void postDeregister() {
		logger.info(buildMessage(name, false, RegistrationStatus.FINISHED));
	}
	
	/**
	 * 
	 * @param objectName
	 * @param register
	 * @param success
	 * @return
	 */
	protected String buildMessage(ObjectName objectName, boolean register, RegistrationStatus status) {
		
		assert objectName != null;
		assert status != null;
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("MBean ");
		stringBuilder.append(objectName);
		stringBuilder.append(' ');
		stringBuilder.append(register ? "registration" : "deregistration");
		stringBuilder.append(" has ");
		stringBuilder.append(status.name().toLowerCase());
		stringBuilder.append('.');
		
		return stringBuilder.toString();
	}

}
