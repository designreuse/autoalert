/**
 * 
 */
package info.geekinaction.autoalert.view;

/**
 * @author lcsontos
 *
 */
public interface IAutoAlertController {

	////////////////////////////////////////////////////////////////
	// Login / Logout
	////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	void onLogin();
	
	/**
	 * 
	 */
	void onLogout();
	
	////////////////////////////////////////////////////////////////
	// Refresh instance information
	////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	void onInstanceInfoRefresh();
	
	/**
	 * 
	 */
	void onInstanceCpuUsageRefresh();
	
	/**
	 * 
	 */
	void onInstanceIoUsageRefresh();
	
	////////////////////////////////////////////////////////////////
	// Refresh storage information
	////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	void onStorageTablespacesRefresh();
	
	/**
	 * 
	 */
	void onStorageDatafilesRefresh();

	////////////////////////////////////////////////////////////////
	// Refresh session information
	////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	void onSessionAllRefresh();
	
	/**
	 * 
	 */
	void onSessionByCpuRefresh();
	
	/**
	 * 
	 */
	void onSessionByIoRefresh();
}
