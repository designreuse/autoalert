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
package info.geekinaction.autoalert.ejb;

/**
 * 
 * Constants for all EJB components
 * 
 * @author lcsontos
 *
 */
public interface EJBConstants {
	
	/**
	 * Name of the mail stateless EJB object.
	 */
	public static final String AUTOALERT_MODEL_NAME = "AutoAlertModel";
	
	/**
	 * Global JNDI name of the object above.
	 */
	public static final String AUTOALERT_MODEL_JNDI = "ejb/AutoAlertModel";
	
	/**
	 * Global JNDI name of the JMS audit queue.
	 */
	public static final String AUTOALERT_AUDIT_QUEUE = "jms/aaAuditQueue";
	
	/**
	 * Global JNDI name of the JMS audit queue factory.
	 */
	public static final String AUTOALERT_AUDIT_QUEUE_FACTORY = "jms/aaAuditQueueFactory";
	
	/**
	 * Measured time slice in minutes
	 */
	public static final int TIME_SLICE = 5;

	/**
	 * 
	 */
	public static final int TIME_SLICE_SEC = 0;

	/**
	 * 
	 */
	public static final int TIME_SLICE_MSEC = 0;

	/**
	 * Timeout interval (5min)
	 */
	public static final int TIMER_INTERVAL = 1 * 60 * 1000;
	
	/**
	 * Timer name
	 */
	public static final String TIMER_NAME = "AUTOALERT_TIMER";
	
}
