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
package info.geekinaction.autoalert;

import info.geekinaction.autoalert.common.util.MBeanUtil;
import info.geekinaction.autoalert.jmx.AutoAlertInstrumentationImpl;
import info.geekinaction.autoalert.jmx.AutoAlertManagementImpl;
import info.geekinaction.autoalert.jmx.IAutoAlertInstrumentation;
import info.geekinaction.autoalert.jmx.IAutoAlertManagement;

import javax.ejb.EJB;
import javax.management.JMException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * Application initializer: takes care of initializing various part
 * of this application on startup and performs cleanup of shutdown.
 * 
 * @author lcsontos
 * 
 */
public final class InitListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(InitListener.class);

	private ObjectName autoAlertInstrumentationObjectName;
	private ObjectName autoAlertManagementObjectName;
	
	@EJB
	private IAutoAlertManagement autoAlertManagement;

	/**
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		// Register instrumentation MBean.
		try {
			autoAlertInstrumentationObjectName = MBeanUtil.registerMBean(IAutoAlertInstrumentation.class, AutoAlertInstrumentationImpl.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Register management MBean.
		try {
			autoAlertManagementObjectName = MBeanUtil.registerMBean(IAutoAlertManagement.class, AutoAlertManagementImpl.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Start EJB timer.
		try {
			autoAlertManagement.startScheduler();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("Context initialized.");
	}
	
	/**
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		// Unregister instrumentation MBean.
		try {
			MBeanUtil.unregisterMBean(autoAlertInstrumentationObjectName);
		} catch (JMException e) {
			logger.error(e.getMessage(), e);
		}
		
		// Unregister management MBean.
		try {
			MBeanUtil.unregisterMBean(autoAlertManagementObjectName);
		} catch (JMException e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("Context destroyed.");
	}
	
}
