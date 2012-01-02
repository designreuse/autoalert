/**
 * 
 */
package info.geekinaction.autoalert;

import info.geekinaction.autoalert.common.util.MBeanUtil;
import info.geekinaction.autoalert.jmx.AutoAlertManagementImpl;
import info.geekinaction.autoalert.jmx.IAutoAlertManagement;

import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 * 
 */
public final class InitListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(InitListener.class);

	private ObjectName mbeanObjectName;

	/**
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			mbeanObjectName = MBeanUtil.registerMBean(IAutoAlertManagement.class, AutoAlertManagementImpl.class);
			logger.info("Context initialized.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			MBeanUtil.unregisterMBean(mbeanObjectName);
			logger.info("Context destroyed.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}	

}
