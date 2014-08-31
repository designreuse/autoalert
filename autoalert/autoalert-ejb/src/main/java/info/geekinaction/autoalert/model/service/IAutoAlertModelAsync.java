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

import java.util.List;
import java.util.Map;


import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * Asynchronous pair of IAutoAlertModel.
 * @author lcsontos
 * 
 */
public interface IAutoAlertModelAsync {

	/**
	 * @see IAutoAlertModel#findParameters()
	 */
	void findParameters(AsyncCallback<Map<ParameterName, Parameter>> callback);

	/**
	 * @see IAutoAlertModel#findDatabase()
	 */
	void findDatabase(AsyncCallback<Database> callback);

	/**
	 * @see IAutoAlertModel#findInstanceCpuUsage(boolean)
	 */
	void findInstanceCpuUsage(boolean alertsOnly,
			AsyncCallback<List<InstanceCpuUsage>> callback);

	/**
	 * @see IAutoAlertModel#findInstanceIoUsage(boolean)
	 */
	void findInstanceIoUsage(boolean alertsOnly,
			AsyncCallback<List<InstanceIoUsage>> callback);

	/**
	 * @see IAutoAlertModel#findTablespaces(boolean)
	 */
	void findTablespaces(boolean alertsOnly,
			AsyncCallback<List<Tablespace>> callback);

	/**
	 * @see IAutoAlertModel#findDatafiles(boolean)
	 */
	void findDatafiles(boolean alertsOnly,
			AsyncCallback<List<Datafile>> callback);

	/**
	 * @see IAutoAlertModel#findSession()
	 */
	void findSession(AsyncCallback<List<Session>> callback);

	/**
	 * @see IAutoAlertModel#findSessionCpuUsage()
	 */
	void findSessionCpuUsage(AsyncCallback<List<SessionCpuUsage>> callback);

	/**
	 * @see IAutoAlertModel#findSessionIoUsage()
	 */
	void findSessionIoUsage(AsyncCallback<List<SessionIoUsage>> callback);
	
}
