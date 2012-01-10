/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import javax.management.MXBean;

/**
 * 
 * Interface of the management MBean, partly the managed EJB object.
 * 
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertManagement {

	/**
	 * Reloads configuration.
	 * 
	 * @return Returns true on success.
	 */
	public void reloadConfiguration();
	
	/**
	 * Set the given system wide parameter to the desired value.
	 * 
	 * @param paramName Name of the parameter.
	 * @param parameterScope Indicates how to set the given parameter. Valid values are: BOTH, MEMORY, DATABASE:
	 * @param value New value of the given parameter.
	 */
	public void setParameter(String paramName, String parameterScope, String value);
	
	/**
	 * Returns the current value of a system wide parameter.
	 *  
	 * @param paramName Name of the parameter.
	 * @return Current value of the given parameter.
	 */
	public String getParameter(String paramName);
	
	/**
	 * Starts the scheduler which monitors threshold violations in the database.
	 */
	public void startScheduler();
	
	/**
	 * Stops the scheduler.
	 */
	public void stopScheduler();
	
	/**
	 * The scheduler can be manually triggered by calling this method.
	 */
	public void triggerScheduler();

}
