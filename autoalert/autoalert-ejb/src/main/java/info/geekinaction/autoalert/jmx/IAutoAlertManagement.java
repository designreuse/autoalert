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

import javax.management.MXBean;

/**
 * 
 * Interface of the management MBean, partly the managed EJB object.
 * 
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertManagement {

	/**
	 * Reloads configuration.
	 * 
	 * @return Returns true on success.
	 */
	public void reloadConfiguration();
	
	/**
	 * Set the given system wide parameter to the desired value.
	 * 
	 * @param paramName Name of the parameter.
	 * @param parameterScope Indicates how to set the given parameter. Valid values are: BOTH, MEMORY, DATABASE:
	 * @param value New value of the given parameter.
	 */
	public void setParameter(String paramName, String parameterScope, String value);
	
	/**
	 * Returns the current value of a system wide parameter.
	 *  
	 * @param paramName Name of the parameter.
	 * @return Current value of the given parameter.
	 */
	public String getParameter(String paramName);
	
	/**
	 * Starts the scheduler which monitors threshold violations in the database.
	 */
	public void startScheduler();
	
	/**
	 * Stops the scheduler.
	 */
	public void stopScheduler();
	
	/**
	 * The scheduler can be manually triggered by calling this method.
	 */
	public void triggerScheduler();

}
