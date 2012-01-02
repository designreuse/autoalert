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

import javax.ejb.Local;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author lcsontos
 * 
 */
@Local
@RemoteServiceRelativePath("model")
public interface IAutoAlertModel extends RemoteService {

	/**
	 * 
	 * @return
	 */
	Map<ParameterName, Parameter> findParameters() throws AutoAlertException;
	
	/**
	 * 
	 * @return
	 */
	Database findDatabase() throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<InstanceCpuUsage> findInstanceCpuUsage(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<InstanceIoUsage> findInstanceIoUsage(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<Tablespace> findTablespaces(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<Datafile> findDatafiles(boolean alertsOnly) throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<Session> findSession() throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<SessionCpuUsage> findSessionCpuUsage() throws AutoAlertException;;

	/**
	 * 
	 * @return
	 */
	List<SessionIoUsage> findSessionIoUsage() throws AutoAlertException;
	
	/**
	 * 
	 */
	void reloadConfiguration();
	
	/**
	 * 
	 */
	void schedule();

}
