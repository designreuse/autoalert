/**
 * 
 */
package info.geekinaction.autoalert.jmx;

import java.util.Set;

import javax.management.MXBean;


/**
 * 
 * MBean interface for instrumenting this application
 * 
 * @author lcsontos
 *
 */
@MXBean(true)
public interface IAutoAlertInstrumentation {

	/**
	 * Retrieves up-time.
	 * 
	 * @return Returns up-time in ISO timestamp format.
	 */
	public String getUptime();
	
	/**
	 * Record runtime information of the specified EJB method call.
	 * 
	 * @param methodName Name of the EJB's method.
	 * @param elapsedTime Runtime in millisecors.
	 */
	public void instrumentMethod(String methodName, long elapsedTime);
	
	/**
	 * Returns the list of instrumented EJB methods.
	 * 
	 * @return Returns the list or null if an error occurred.
	 */
	public Set<String> getEJBMethods();
	
	/**
	 * Returns the number of calls to the specified EJB method.
	 * 
	 * @param methodName Name of the method.
	 * @return Call count or null on error.
	 */
	public int getCallCountForMethod(String methodName);
	
	/**
	 * Returns the average runtime of the specified EJB method.
	 * 
	 * @param methodName Name of the method.
	 * @return Average runtime in milliseconds or null on error.
	 */
	public double getRuntimeStatForMethod(String methodName);
	
}
