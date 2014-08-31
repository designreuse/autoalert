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
 * Enumerates JPQL named queries.
 * 
 * @author lcsontos
 *
 */
public enum AutoAlertQuery {

	FIND_PARAMETERS ("findParameters"),
	FIND_DATABASE ("findDatabase"),
	FIND_INSTANCE_CPU_USAGE ("findInstanceCpuUsage"),
	FIND_INSTANCE_IO_USAGE ("findInstanceIoUsage"),
	FIND_TABLESPACES ("findTablespaces"),
	FIND_DATAFILES ("findDatafiles"),
	FIND_SESSION ("findSession"),
	FIND_SESSION_CPU_USAGE ("findSessionCpuUsage"),
	FIND_SESSION_IO_USAGE ("findSessionIoUsage"),
	COUNT_INCIDENTS ("countIncidents");
	
	private String queryName;
	
	/**
	 * 
	 * @param queryName
	 */
	private AutoAlertQuery(String queryName) {
		this.queryName = queryName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getQueryName() {
		return queryName;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return queryName;
	}
}
