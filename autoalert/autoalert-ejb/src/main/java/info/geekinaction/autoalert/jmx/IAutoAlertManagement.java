/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import javax.management.MXBean;

/**
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertManagement {

	/**
	 * Reloads configuration.
	 * @return Returns true on success.
	 */
	public void reloadConfiguration();
	
	/**
	 * 
	 * @param paramName
	 * @param value
	 */
	public void setParameter(String paramName, String value);
	
	/**
	 * 
	 * @param paramName
	 * @return
	 */
	public String getParameter(String paramName);
	
	/**
	 * 
	 */
	public void startScheduler();
	
	/**
	 * 
	 */
	public void stopScheduler();
	
	/**
	 * 
	 */
	public void triggerScheduler();

}
