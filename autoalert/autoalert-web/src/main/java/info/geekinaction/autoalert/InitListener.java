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

	private ObjectName autoAlertInstrumentationObjectName;
	private ObjectName autoAlertManagementObjectName;
	
	@EJB
	private IAutoAlertManagement autoAlertManagement;

	/**
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			autoAlertInstrumentationObjectName = MBeanUtil.registerMBean(IAutoAlertInstrumentation.class, AutoAlertInstrumentationImpl.class);
			autoAlertManagementObjectName = MBeanUtil.registerMBean(IAutoAlertManagement.class, AutoAlertManagementImpl.class);
			autoAlertManagement.startScheduler();
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
			MBeanUtil.unregisterMBean(autoAlertInstrumentationObjectName);
			MBeanUtil.unregisterMBean(autoAlertManagementObjectName);
			logger.info("Context destroyed.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
