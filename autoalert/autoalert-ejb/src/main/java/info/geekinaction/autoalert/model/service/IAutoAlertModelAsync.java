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
 * @author lcsontos
 * 
 */
public interface IAutoAlertModelAsync {

	/**
	 * 
	 * @param callback
	 */
	void findParameters(AsyncCallback<Map<ParameterName, Parameter>> callback);

	/**
	 * 
	 * @param database
	 * @param callback
	 */
	void findDatabase(AsyncCallback<Database> callback);

	/**
	 * 
	 * @param alertsOnly
	 * @param callback
	 */
	void findInstanceCpuUsage(boolean alertsOnly,
			AsyncCallback<List<InstanceCpuUsage>> callback);

	/**
	 * 
	 * @param alertsOnly
	 * @param callback
	 */
	void findInstanceIoUsage(boolean alertsOnly,
			AsyncCallback<List<InstanceIoUsage>> callback);

	/**
	 * 
	 * @param alertsOnly
	 * @param callback
	 */
	void findTablespaces(boolean alertsOnly,
			AsyncCallback<List<Tablespace>> callback);

	/**
	 * 
	 * @param alertsOnly
	 * @param callback
	 */
	void findDatafiles(boolean alertsOnly,
			AsyncCallback<List<Datafile>> callback);

	/**
	 * 
	 * @param callback
	 */
	void findSession(AsyncCallback<List<Session>> callback);

	/**
	 * 
	 * @param callback
	 */
	void findSessionCpuUsage(AsyncCallback<List<SessionCpuUsage>> callback);

	/**
	 * 
	 * @param callback
	 */
	void findSessionIoUsage(AsyncCallback<List<SessionIoUsage>> callback);
	
}
