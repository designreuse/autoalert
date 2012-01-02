/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import java.util.List;

import javax.management.MXBean;


/**
 * MBean interface for managing this application
 * 
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertManagement {

	/**
	 * Reloads configuration.
	 * @return Returns true on success.
	 */
	public boolean reloadConfiguration();
	
	/**
	 * Retrieves up-time.
	 * @return Returns up-time in ISO timestamp format.
	 */
	public String getUptime();
	
	/**
	 * 
	 * @param methodName
	 * @param elapsedTime
	 */
	public void instrumentMethod(String methodName, long elapsedTime);
	
	/**
	 * Returns the list of instrumented EJB methods.
	 * @return Returns the list or null if an error occurred.
	 */
	public List<String> getEJBMethods();
	
	/**
	 * Returns the number of calls to the specified EJB method.
	 * @param methodName Name of the method.
	 * @return Call count or null on error.
	 */
	public Integer getCallCountForMethod(String methodName);
	
	/**
	 * Returns the average runtime of the specified EJB method.
	 * @param methodName Name of the method.
	 * @return Average runtime in milliseconds or null on error.
	 */
	public Long getRuntimeStatForMethod(String methodName);
	
}
