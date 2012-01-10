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
