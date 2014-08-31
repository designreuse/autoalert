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
package info.geekinaction.autoalert.model.service;

import info.geekinaction.autoalert.model.domain.Database;
import info.geekinaction.autoalert.model.domain.Datafile;
import info.geekinaction.autoalert.model.domain.InstanceCpuUsage;
import info.geekinaction.autoalert.model.domain.InstanceIoUsage;
import info.geekinaction.autoalert.model.domain.Parameter;
import info.geekinaction.autoalert.model.domain.ParameterName;
import info.geekinaction.autoalert.model.domain.Session;
import info.geekinaction.autoalert.model.domain.SessionCpuUsage;
import info.geekinaction.autoalert.model.domain.SessionIoUsage;
import info.geekinaction.autoalert.model.domain.Tablespace;
import info.geekinaction.autoalert.model.exception.AutoAlertException;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * This interface constitutes the business model of the application
 * 
 * @author lcsontos
 * 
 */
@RemoteServiceRelativePath("model")
public interface IAutoAlertModel extends RemoteService {

	/**
	 * 
	 * Returns the currently valid paramters of the application
	 *  
	 * @return A parameter map.
	 */
	public Map<ParameterName, Parameter> findParameters() throws AutoAlertException;
	
	/**
	 * 
	 * Returns an object which describes the properties of that database instance which
	 * we are connected to.
	 * 
	 * @return A database object.
	 * 
	 */
	public Database findDatabase() throws AutoAlertException;

	/**
	 * 
	 * Returns the CPU usage history of the current database instance
	 * in an 1 hour moving window.
	 * 
	 * @param alertsOnly Returns only those records which indicates threshold violations.
	 * @return CPU usage history data.
	 * 
	 */
	public List<InstanceCpuUsage> findInstanceCpuUsage(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * Returns the I/O usage history of the current database instance
	 * in an 1 hour moving window.
	 * 
	 * @param alertsOnly Returns only those records which indicates threshold violations.
	 * @return I/O usage history data.
	 * 
	 */
	public List<InstanceIoUsage> findInstanceIoUsage(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * Returns the list of tablespaces of the database.
	 *
	 * @param alertsOnly Returns only those records which indicates threshold violations.
	 * @return The list of tablespaces of the database.
	 * 
	 */
	public List<Tablespace> findTablespaces(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * Returns the list of data files of the database.
	 *
	 * @param alertsOnly Returns only those records which indicates threshold violations.
	 * @return The list of data files of the database.
	 * 
	 */
	public List<Datafile> findDatafiles(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * Returns a list of connected sessions to the database.
	 * 
	 * @return List of sessions.
	 */
	public List<Session> findSession() throws AutoAlertException;

	/**
	 * 
	 * Returns the top CPU consumer session of the database.
	 * 
	 * @return TOP CPU consumer sessions.
	 * 
	 */
	public List<SessionCpuUsage> findSessionCpuUsage() throws AutoAlertException;;

	/**
	 * 
	 * Returns the top I/O consumer session of the database.
	 * 
	 * @return TOP I/O consumer sessions.
	 * 
	 */
	public List<SessionIoUsage> findSessionIoUsage() throws AutoAlertException;
	
}
