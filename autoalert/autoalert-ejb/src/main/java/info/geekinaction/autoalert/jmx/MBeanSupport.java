/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import info.geekinaction.autoalert.common.util.LogUtil;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * Base class of all MBeans, which implements common life-cycle callback methods.
 * 
 * @author lcsontos
 *
 */
public class MBeanSupport implements MBeanRegistration {
	
	private static final Logger logger = Logger.getLogger(MBeanSupport.class);
	private static final String MESSAGE = "MBean {0} {1} has {2}.";
	
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
		Object[] params = buildMessageParams(name, true, RegistrationStatus.STARTED);
		LogUtil.log(logger, Level.INFO, MESSAGE, null, params);
		this.name = name;
		return name;
	}

	/**
	 * @see javax.management.MBeanRegistration#postRegister(java.lang.Boolean)
	 */
	@Override
	public void postRegister(Boolean registrationDone) {
		Object[] params = buildMessageParams(name, true, registrationDone ? RegistrationStatus.FINISHED : RegistrationStatus.FAILED);
		LogUtil.log(logger, Level.INFO, MESSAGE, null, params);
	}

	/**
	 * @see javax.management.MBeanRegistration#preDeregister()
	 */
	@Override
	public void preDeregister() throws Exception {
		Object[] params = buildMessageParams(name, false, RegistrationStatus.STARTED);
		LogUtil.log(logger, Level.INFO, MESSAGE, null, params);
	}

	/**
	 * @see javax.management.MBeanRegistration#postDeregister()
	 */
	@Override
	public void postDeregister() {
		Object[] params = buildMessageParams(name, false, RegistrationStatus.FINISHED);
		LogUtil.log(logger, Level.INFO, MESSAGE, null, params);
	}
	
	/**
	 * Builds message parameters
	 * 
	 * @param objectName Object of MBean.
	 * @param register True if the current action is registration.
	 * @param success True if the current operation was successdul.
	 * @return Parameters of the log message.
	 */
	protected Object[] buildMessageParams(ObjectName objectName, boolean register, RegistrationStatus status) {
		
		assert objectName != null;
		assert status != null;
		
		Object[] retval = new Object[] { objectName, register ? "registration" : "deregistration", status.name().toLowerCase() };
		
		return retval;
	}

}
