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
package info.geekinaction.autoalert.model.incident;

/**
 * 
 * This interface is a special view of the EJB implementation in terms of handling detected incidents.
 * 
 * @author lcsontos
 *
 */
public interface IAutoAlertIncidentHandler {
	
	/**
	 * 
	 * This method stores a detected threshold violation incident
	 * IF AND ONLY IF the same incident has not been reported within
	 * a given time frame.
	 * 
	 * Each incident has a checksum which should be unique to that incident
	 * within the time frame above.
	 * 
	 * @param incident An incident to be reported.
	 * @return Return true if this incident can be considered new beyond the specified time frame. 
	 * 
	 * @see info.geekinaction.autoalert.model.incident.AutoAlertIncidentListener
	 */
	public boolean storeIncident(AutoAlertIncident incident);

}
