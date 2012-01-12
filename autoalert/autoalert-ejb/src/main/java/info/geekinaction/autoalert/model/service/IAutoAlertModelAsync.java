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
