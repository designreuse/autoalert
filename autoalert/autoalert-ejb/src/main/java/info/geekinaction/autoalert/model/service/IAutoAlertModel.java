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
