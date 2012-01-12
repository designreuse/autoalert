/**
 * 
 */
package info.geekinaction.autoalert.rpc;

import org.apache.log4j.Logger;

import info.geekinaction.autoalert.security.IAutoAlertSecurity;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * NOT IMPLEMENTED YET.
 * 
 * RPC service for implemention server side login / logout.
 * 
 * @author lcsontos
 *
 */
public class AutoAlertSecurityDelegate extends RemoteServiceServlet implements IAutoAlertSecurity {

	private static final Logger logger = Logger.getLogger(AutoAlertSecurityDelegate.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
