/*
 * Copyright (C) 2010 - present, Laszlo Csontos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
