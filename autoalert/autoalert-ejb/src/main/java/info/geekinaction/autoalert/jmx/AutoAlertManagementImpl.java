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

import static info.geekinaction.autoalert.ejb.EJBConstants.AUTOALERT_MODEL_JNDI;

import javax.naming.NamingException;

import info.geekinaction.autoalert.common.util.CachingServiceLocator;

import org.apache.log4j.Logger;

/**
 * @author lcsontos
 *
 */
public class AutoAlertManagementImpl extends MBeanSupport implements IAutoAlertManagement {

	private static final Logger logger = Logger.getLogger(AutoAlertManagementImpl.class);

	// Local EJB proxy.
	private IAutoAlertManagement ejbProxy;

	/**
	 * @see info.geekinaction.autoalert.jmx.IAutoAlertManagement#reloadConfiguration()
	 */
	@Override
	public void reloadConfiguration() {
		try {
			getEjbProxy().reloadConfiguration();
			logger.info("Configuration reloaded successfully.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	@Override
	public String getParameter(String paramName) {
		try {
			return getEjbProxy().getParameter(paramName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void setParameter(String paramName, String parameterScope, String value) {
		try {
			getEjbProxy().setParameter(paramName, parameterScope, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void startScheduler() {
		try {
			getEjbProxy().startScheduler();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void stopScheduler() {
		try {
			getEjbProxy().stopScheduler();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void triggerScheduler() {
		try {
			getEjbProxy().triggerScheduler();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private IAutoAlertManagement getEjbProxy() throws NamingException {
		if (ejbProxy == null) {
			ejbProxy = CachingServiceLocator.lookupStatelessEJB(AUTOALERT_MODEL_JNDI, IAutoAlertManagement.class);
		}
		return ejbProxy;
	}
	

}
