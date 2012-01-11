/**
 * 
 */
package info.geekinaction.autoalert.view;

import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author lcsontos
 *
 */
public interface IAutoAlertController extends ClickHandler {

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
