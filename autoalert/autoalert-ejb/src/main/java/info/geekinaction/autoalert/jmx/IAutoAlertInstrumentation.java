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

import java.util.Set;

import javax.management.MXBean;


/**
 * 
 * MBean interface for instrumenting this application
 * 
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertInstrumentation {

	/**
	 * Retrieves up-time.
	 * 
	 * @return Returns up-time in ISO timestamp format.
	 */
	public String getUptime();
	
	/**
	 * Record runtime information of the specified EJB method call.
	 * 
	 * @param methodName Name of the EJB's method.
	 * @param elapsedTime Runtime in millisecors.
	 */
	public void instrumentMethod(String methodName, long elapsedTime);
	
	/**
	 * Returns the list of instrumented EJB methods.
	 * 
	 * @return Returns the list or null if an error occurred.
	 */
	public Set<String> getEJBMethods();
	
	/**
	 * Returns the number of calls to the specified EJB method.
	 * 
	 * @param methodName Name of the method.
	 * @return Call count or null on error.
	 */
	public int getCallCountForMethod(String methodName);
	
	/**
	 * Returns the average runtime of the specified EJB method.
	 * 
	 * @param methodName Name of the method.
	 * @return Average runtime in milliseconds or null on error.
	 */
	public double getRuntimeStatForMethod(String methodName);
	
}
